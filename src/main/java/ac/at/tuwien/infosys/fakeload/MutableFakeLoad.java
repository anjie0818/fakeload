package ac.at.tuwien.infosys.fakeload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by martensigwart on 19.07.17.
 */
public final class MutableFakeLoad extends AbstractFakeLoad {

    private long duration;
    private TimeUnit unit;
    private int repetitions;
    private int cpuLoad;
    private long memoryLoad;
    private long diskIOLoad;
    private long netIOLoad;

    private List<FakeLoad> loads;


    public MutableFakeLoad() {
        this.loads = new ArrayList<>();
    }

    public MutableFakeLoad(long duration, TimeUnit unit) {
        this();
        this.duration = duration;
        this.unit = unit;
    }


    @Override
    public FakeLoad withDuration(long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
        return this;
    }

    @Override
    public FakeLoad withCpuLoad(int cpuLoad) {
        this.cpuLoad = cpuLoad;
        return this;
    }

    @Override
    public FakeLoad withMemoryLoad(long amount, MemoryUnit unit) {
        this.memoryLoad = amount;
        return this;
    }

    @Override
    public FakeLoad withDiskIOLoad(long diskIOLoad) {
        this.diskIOLoad = diskIOLoad;
        return this;
    }

    @Override
    public FakeLoad withNetIOLoad(long netIOLoad) {
        this.netIOLoad = netIOLoad;
        return this;
    }

    @Override
    public FakeLoad addLoad(FakeLoad load) {
        if (load.contains(this)) {
            throw new IllegalArgumentException("The calling FakeLoad is part of the load it is trying to add.");
        }
        loads.add(load);
        return this;
    }

    @Override
    public FakeLoad addLoads(Collection<FakeLoad> loads) {
        loads.forEach(l -> {
            this.addLoad(l);
        });
        return this;
    }

    @Override
    public Collection<FakeLoad> getLoads() {
        return loads;
    }

}