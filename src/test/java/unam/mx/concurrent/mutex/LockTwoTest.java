package unam.mx.concurrent.mutex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author miguel
 */
public class LockTwoTest {

    private final static int THREADS = 2;
    private final static int COUNT = 1024;
    private final static int PER_THREAD = COUNT / THREADS;
    private final static int TIMEOUT = 10000;
    private final Thread[] threads = new Thread[THREADS];
    private int counter = 0;
    private final LockTwo instance = new LockTwo();

    @Test
    public void testLockTwo() throws InterruptedException {
        System.out.println("Parallel execution");
        ThreadID.reset();
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new MyThread();
        }
        for (int i = 0; i < THREADS; i++) {
            threads[i].start();
        }
        for (int i = 0; i < THREADS; i++) {
            threads[i].join(TIMEOUT);
        }
        Assertions.assertEquals(COUNT, counter);
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < PER_THREAD; i++) {
                instance.lock();
                try {
                    counter = counter + 1;
                } finally {
                    instance.unlock();
                }
            }
        }
    }

}
