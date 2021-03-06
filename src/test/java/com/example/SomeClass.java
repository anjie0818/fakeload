package com.example;

import com.martensigwart.fakeload.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SomeClass {

    private static final Logger log = LoggerFactory.getLogger(SomeClass.class);

    private FakeLoadExecutor executor = FakeLoadExecutors.newDefaultExecutor();

    public void someMethod() {
        log.info("Entered someMethod()");

        // Execute FakeLoad
//        FakeLoad fakeLoad = FakeLoads.create(100, "50%", "1024m");
        FakeLoad fakeLoad = FakeLoads.create().lasting(10, TimeUnit.SECONDS)
//                .withCpu(50);
//                .withMemory(200, MemoryUnit.MB);
                .withDiskInput(100, MemoryUnit.MB)
                .withDiskOutput(100, MemoryUnit.MB);
//        FakeLoad fakeLoad2 = FakeLoads.create().lasting(5, TimeUnit.SECONDS)
//                .withDiskInput(200, MemoryUnit.KB);
//
//        FakeLoad fakeLoad3 = FakeLoads.create().lasting(5, TimeUnit.SECONDS)
//                .withCpu(50)
//                .withDiskInput(300, MemoryUnit.KB);

        executor.execute(fakeLoad);
        log.info("CPU simulation successful");
//        executor.execute(fakeLoad2);        // should fail if no input.tmp file exists
//        executor.execute(fakeLoad3);
        // some code
        log.info("Leaving someMethod()...");
    }

    public void someOtherMethod() {
        log.info("Entered someOtherMethod()");

        // Create FakeLoad with Builder pattern
        FakeLoad fakeload = new FakeLoadBuilder(30, TimeUnit.SECONDS)
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(10).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(20).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(30).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(40).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(50).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(60).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(70).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(80).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(90).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(100).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(90).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(80).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(70).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(60).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(50).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(40).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(30).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(20).build())
                .addLoad(new FakeLoadBuilder(4, TimeUnit.SECONDS).withCpu(10).build())
                .addLoad(new FakeLoadBuilder(20, TimeUnit.SECONDS).build())
                .build();
//
//        // Execute FakeLoad
//        executor.execute(fakeload);

//        fakeload = new FakeLoadBuilder(20, TimeUnit.SECONDS)
//                .withCpu(20).withMemory(100, MemoryUnit.KB)
//                .addLoad(new FakeLoadBuilder(20, TimeUnit.SECONDS)
//                    .withCpu(40).withMemory(200, MemoryUnit.KB).build())
//                .addLoad(new FakeLoadBuilder(20, TimeUnit.SECONDS)
//                    .withCpu(60).withMemory(300, MemoryUnit.KB).build())
//                .addLoad(new FakeLoadBuilder(20, TimeUnit.SECONDS)
//                    .withCpu(80).withMemory(400, MemoryUnit.KB).build())
//                .addLoad(new FakeLoadBuilder(20, TimeUnit.SECONDS)
//                    .withCpu(100).withMemory(500, MemoryUnit.KB).build())
//                .build();

//        FakeLoad fakeload = new FakeLoadBuilder(0, TimeUnit.SECONDS)
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(50, MemoryUnit.MB).build())
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(100, MemoryUnit.MB).build())
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(150, MemoryUnit.MB).build())
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(200, MemoryUnit.MB).build())
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(250, MemoryUnit.MB).build())
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(300, MemoryUnit.MB).build())
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(350, MemoryUnit.MB).build())
//                .addLoad(new FakeLoadBuilder(30, TimeUnit.SECONDS)
//                        .withDiskOutput(400, MemoryUnit.MB).build())
//                .build();

//        fakeload = new FakeLoadBuilder(0, TimeUnit.SECONDS)
//                .addLoad(new FakeLoadBuilder(60, TimeUnit.SECONDS)
//                        .withDiskOutput(1, MemoryUnit.GB).build())
//                .build();

        executor.execute(fakeload);


//        // Create LoadPattern with Factory pattern
//        pattern = LoadPatterns.createLoadPattern();
//        pattern.addLoad(60, TimeUnit.SECONDS, "30%", "2048k");
//        pattern.addIntervalLoad(200, TimeUnit.MILLISECONDS, 10, TimeUnit.SECONDS, "50%", "1024m");
//
//        // Execute AbstractFakeLoad
//        fakeLoad = new AbstractFakeLoad(pattern);
//        fakeLoad.execute();

        // some code
        log.info("Leaving someOtherMethod()...");
    }

    public void yetAnotherMethod() {
        log.info("Entered yetAnotherMethod()");

        FakeLoad fakeLoad = FakeLoads.create()
                .lasting(30, TimeUnit.SECONDS)
                .withCpu(80)
                .withMemory(300, MemoryUnit.MB);

        executor.execute(fakeLoad);

        log.info("Leaving yetAnotherMethod()");
    }

}
