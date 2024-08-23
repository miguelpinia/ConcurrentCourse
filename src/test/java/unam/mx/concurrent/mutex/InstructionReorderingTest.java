package unam.mx.concurrent.mutex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

/**
 * The results we expect are:
 *
 * x = 0, y = 1: threadA runs to completion before threadB starts.
 *
 * x = 1, y = 0: threadB runs to completion before threadA starts.
 *
 * x = 1, y = 1: their instructions are interleaved.
 *
 * No one can expect x = 0, y = 0, which may happen as the test results showed.
 *
 * The actions in each thread have no dataflow dependence on each other, and
 * accordingly can be executed out of order. (Even if they are executed in
 * order, the timing by which caches are flushed to main memory can make it
 * appear, from the perspective of threadB, that the assignments in threadA
 * occurred in the opposite order.
 *
 * ThreadA: --> X = b(0) -------- reorder ------------> a = 1
 *
 * ThreadB: -----------> b = 1 ---------> Y = a(0)
 *
 * @author miguel
 */
public class InstructionReorderingTest {

    static int x, y, a, b;

    @BeforeEach
    public void init() {
        x = y = a = b = 0;
    }

    @RepeatedTest(100000)
    public void test() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread threadB = new Thread(() -> {
            b = 1;
            y = a;
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        Assertions.assertFalse(x == 0 && y == 0);
    }

}
