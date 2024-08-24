package unam.mx.concurrent.mutex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

/**
 *
 * @author miguel
 */
public class NoLockTest {

    private final static int THREADS = Runtime.getRuntime().availableProcessors();
    private final static int COUNT = 1024 * 10000;
    private final static int PER_THREAD = COUNT / THREADS;
    private final static int TIMEOUT = 1000;
    private final Thread[] threads = new Thread[THREADS];
    private int counter;

    @RepeatedTest(100)
    public void testNoLock() throws InterruptedException {
        System.out.println("Executing test for no lock concurrent increments");
        System.out.println("Available processors: " + THREADS);
        long time = System.currentTimeMillis();
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < PER_THREAD; j++) {
                    counter = counter + 1;
                }
            });
        }
        for (int i = 0; i < THREADS; i++) {
            threads[i].start();
        }
        for (int i = 0; i < THREADS; i++) {
            threads[i].join(TIMEOUT);
        }
        time = System.currentTimeMillis() - time;
        System.out.println(String.format("The value for the counter is: %d", counter));
        System.out.println(String.format("Total time: %d ms", time));
        Assertions.assertEquals(COUNT, counter);
    }

}
