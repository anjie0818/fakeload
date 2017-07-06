package ac.at.tuwien.infosys.fakeload;


import java.util.concurrent.TimeUnit;

/**
 * Created by martensigwart on 29.06.17.
 */
public class SimpleLoadPattern extends AbstractLoadPattern {


    /**
     * Duration of the load pattern
     */
    private long duration;

    /**
     * CPU load in percent
     */
    private int cpuLoad;

    /**
     * Memory load in bytes
     */
    private long memoryLoad;



    SimpleLoadPattern() {
        super();
    }

    @Override
    public void addLoad(LoadPattern pattern) {

    }

    @Override
    public void addLoad(long duration, String... loads) {

    }

    @Override
    public void addLoad(long duration, TimeUnit unit, String... loads) {

    }

    @Override
    public void addIntervalLoad(long duration, TimeUnit unitDuration, long interval, TimeUnit unitInterval, String... loads) {

    }

    @Override
    public void removeLoad(LoadPattern pattern) {

    }

    @Override
    public boolean contains(LoadPattern pattern) {
        return false;
    }



}
