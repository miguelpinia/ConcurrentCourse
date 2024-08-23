package unam.mx.concurrent.mutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author miguel
 */
public class LockTwo implements Lock {

    private volatile int victim;

    @Override
    public void lock() {
        int i = ThreadID.get();
        victim = i;
        while (victim == i) {
        }
    }

    @Override
    public void unlock() {
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tryLock(long l, TimeUnit tu) throws InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
