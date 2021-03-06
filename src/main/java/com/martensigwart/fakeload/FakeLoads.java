package com.martensigwart.fakeload;


/**
 * Factory and utility methods for {@link FakeLoad} classes defined in this package.
 *
 * <p> Contains methods that create and return instances of concrete types of the {@code FakeLoad} interface.
 * The actual type of the objects returned depends on the parameters passed to the factory methods.
 *
 * @since 1.8
 * @see FakeLoad
 * @author Marten Sigwart
 */
public final class FakeLoads {

    /**
     * Creates a new empty {@code FakeLoad} instance.
     * @return a newly created {@code LoadPattern} instance
     */
    public static FakeLoad create() {
        return new SimpleFakeLoad();
    }


//    /**
//     * Creates a new {@code FakeLoad} object with the specified parameters.
//     *
//     * <p> Parameter <b>loads</b> is a String array containing load instructions for different simulation aspects.
//     * <ul>
//     *  <li> CPU load is specified as percentage, e.g. for a CPU load of 60 percent specify "60%"</li>
//     *  <li> Memory can be specified as Bytes(b), kB(k), MB(m) or GB(g), e.g. for a memory allocation of 1024 MB specify "1024m"</li>
//     * </ul>
//     *
//     * @param duration how long the specified loads shall be executed in milliseconds (ms)
//     * @param loads strings containing specific load instructions for different aspects.
//     */
//    public static FakeLoad create(long duration, String... loads) {
//        // TODO return FakeLoad with provided parameters
//        return new SimpleFakeLoad();
//    }


    // Suppress default constructor for non-instantiability
    private FakeLoads() {
        throw new AssertionError();
    }


}
