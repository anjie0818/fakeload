package com.martensigwart.fakeload;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.*;

/**
 * Implementation of interface {@link FakeLoad} representing composite
 * {@code FakeLoad} objects.
 *
 * A {@code CompositeFakeLoad} can contain own load instructions as well as
 * multiple "inner" or "children" fake loads. In detail it is composed of a
 * {@link FakeLoad} object containing its own load instructions and a
 * collection of {@code FakeLoad} objects representing its children.
 *
 * @since 1.8
 * @author Marten Sigwart
 */
final class CompositeFakeLoad extends AbstractFakeLoad {

    /**
     * This object's own load instructions
     */
    private final SimpleFakeLoad ownLoad;

    /**
     * This object's inner/children loads
     */
    private final ImmutableList<FakeLoad> innerLoads;

    CompositeFakeLoad(SimpleFakeLoad ownLoad, List<FakeLoad> innerLoads, int repetitions) {
        super(repetitions);
        this.ownLoad = (SimpleFakeLoad) checkNotNull(ownLoad).repeat(1);
        this.innerLoads = ImmutableList.copyOf(checkNotNull(innerLoads));
    }

    CompositeFakeLoad(List<FakeLoad> innerLoads, int repetitions) {
        this(new SimpleFakeLoad(), innerLoads, repetitions);
    }

    CompositeFakeLoad(List<FakeLoad> innerLoads) {
        this(innerLoads, 1);
    }

    CompositeFakeLoad() {
        this(new ArrayList<>());
    }

    @Override
    public FakeLoad lasting(long duration, TimeUnit unit) {
        SimpleFakeLoad newOwnLoad = (SimpleFakeLoad) ownLoad.lasting(duration, unit);
        return new CompositeFakeLoad(newOwnLoad, innerLoads, getRepetitions());
    }

    @Override
    public FakeLoad repeat(int noOfRepetitions) {
        return new CompositeFakeLoad(ownLoad, innerLoads, noOfRepetitions);
    }

    @Override
    public FakeLoad withCpu(int cpuLoad) {
        SimpleFakeLoad newOwnLoad = (SimpleFakeLoad) ownLoad.withCpu(cpuLoad);
        return new CompositeFakeLoad(newOwnLoad, innerLoads, getRepetitions());
    }

    @Override
    public FakeLoad withMemory(long amount, MemoryUnit unit) {
        SimpleFakeLoad newOwnLoad = (SimpleFakeLoad) ownLoad.withMemory(amount, unit);
        return new CompositeFakeLoad(newOwnLoad, innerLoads, getRepetitions());
    }

    @Override
    public FakeLoad withDiskInput(long load, MemoryUnit unit) {
        SimpleFakeLoad newOwnLoad = (SimpleFakeLoad) ownLoad.withDiskInput(load, unit);
        return new CompositeFakeLoad(newOwnLoad, innerLoads, getRepetitions());
    }

    @Override
    public FakeLoad withDiskOutput(long load, MemoryUnit unit) {
        SimpleFakeLoad newOwnLoad = (SimpleFakeLoad) ownLoad.withDiskOutput(load, unit);
        return new CompositeFakeLoad(newOwnLoad, innerLoads, getRepetitions());
    }


    @Override
    public FakeLoad addLoad(FakeLoad load) {
        checkNotNull(load);

        List<FakeLoad> newLoads;
        if (this.innerLoads.isEmpty()) {
            newLoads = ImmutableList.of(load);
        } else {
            newLoads = ImmutableList.<FakeLoad>builder().addAll(this.innerLoads).add(load).build();
        }
        return new CompositeFakeLoad(ownLoad, newLoads, getRepetitions());
    }

    @Override
    public FakeLoad addLoads(Collection<FakeLoad> loads) {
        checkNotNull(loads);

        List<FakeLoad> newLoads;
        if (this.innerLoads.isEmpty()) {
            newLoads = ImmutableList.copyOf(loads);
        } else {
            newLoads = ImmutableList.<FakeLoad>builder().addAll(this.innerLoads).addAll(loads).build();
        }
        return new CompositeFakeLoad(ownLoad, newLoads, getRepetitions());
    }

    @Override
    public boolean contains(FakeLoad load) {
        if (this.equals(load)) {
            return true;
        }

        // recursively check if load is contained in child innerLoads
        for (FakeLoad l : this.getInnerLoads()) {
            if (l.contains(load)) {
                return true;
            }
        }

        // load not found anywhere
        return false;
    }

    @Override
    public Collection<FakeLoad> getInnerLoads() {
        return innerLoads;
    }

    @Override
    public int getCpu() {
        return ownLoad.getCpu();
    }

    @Override
    public long getMemory() {
        return ownLoad.getMemory();
    }

    @Override
    public long getDiskInput() {
        return ownLoad.getDiskInput();
    }

    @Override
    public long getDiskOutput() {
        return ownLoad.getDiskOutput();
    }

    @Override
    public long getDuration() {
        return ownLoad.getDuration();
    }

    @Override
    public TimeUnit getTimeUnit() {
        return ownLoad.getTimeUnit();
    }

    @Override
    public Iterator<FakeLoad> iterator() {
        Iterator<FakeLoad> iterator = null;
        for (int i=0; i<getRepetitions(); i++) {
            if (iterator == null) {
                iterator = ownLoad.iterator();
            } else {
                iterator = Iterators.concat(iterator, ownLoad.iterator());
            }
            for (FakeLoad load : innerLoads) {
                iterator = Iterators.concat(iterator, load.iterator());
            }
        }
        return iterator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeFakeLoad fakeload = (CompositeFakeLoad) o;
        return Objects.equal(ownLoad, fakeload.ownLoad) &&
                Objects.equal(innerLoads, fakeload.innerLoads);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ownLoad, innerLoads);
    }

    @Override
    public String toString() {
        return "CompositeFakeLoad{" +
                "ownLoad=" + ownLoad +
                ", innerLoads=" + innerLoads +
                '}';
    }

    // readObject method for the serialization proxy pattern
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }
}
