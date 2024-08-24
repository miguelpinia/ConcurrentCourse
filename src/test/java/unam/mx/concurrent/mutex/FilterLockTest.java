package unam.mx.concurrent.mutex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author miguel
 */
public class FilterLockTest {

    private final static int THREADS = Runtime.getRuntime().availableProcessors();
    private final static int COUNT = 1024 * 1000;
    private final static int PER_THREAD = COUNT / THREADS;
    private final static int TIMEOUT = 10000;
    private final Thread[] threads = new Thread[THREADS];
    private int counter;

    private final FilterLock instance = new FilterLock(THREADS);

    @Test
    public void testFilterLock() throws InterruptedException {
        System.out.println("Executing Filter Lock Test");
        System.out.println("Available processors: " + THREADS);
        long time = System.currentTimeMillis();
        ThreadID.reset();
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int t = 0; t < PER_THREAD; t++) {
                    instance.lock();
                    try {
                        counter = counter + 1;
                    } finally {
                        instance.unlock();
                    }
                }
            });
        }
        for (int i = 0; i < THREADS; i++) {
            threads[i].start();
        }
        for (int i = 0; i < THREADS; i++) {
            threads[i].join(TIMEOUT);
        }
        Assertions.assertEquals(COUNT, counter);
        time = System.currentTimeMillis() - time;
        System.out.println(String.format("The value for the counter is: %d", counter));
        System.out.println(String.format("Total time: %d ms", time));
    }

}
