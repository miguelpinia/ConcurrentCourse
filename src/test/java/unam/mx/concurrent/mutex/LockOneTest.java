package unam.mx.concurrent.mutex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author miguel
 */
public class LockOneTest {

    private final static int THREADS = 2;
    private final static int COUNT = 1024;
    private final static int PER_THREAD = COUNT / THREADS;
    private final static int TIMEOUT = 10000;
    private final Thread[] threads = new Thread[THREADS];
    private int counter = 0;

    private final LockOne instance = new LockOne();

    ;

    public LockOneTest() {
    }

    /**
     * Test of lock method, of class LockOne.
     *
     * @throws InterruptedException
     */
    @Test
    public void testLockOne() throws InterruptedException {
        System.out.println("Parallel execution");
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
    }

}
