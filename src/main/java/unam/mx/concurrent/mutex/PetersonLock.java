package unam.mx.concurrent.mutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author miguel
 */
public class PetersonLock implements Lock {

    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;

    @Override
    public void lock() {
        int i = ThreadID.get();
        int j = 1 - i;
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) {
        }
    }

    @Override
    public void unlock() {
        int i = ThreadID.get();
        flag[i] = false;
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
