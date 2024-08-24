package unam.mx.concurrent.mutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author miguel
 */
public class FilterLock implements Lock {

    private static final int IDLE = -1;
    private volatile int[] level;
    private volatile int[] victim;
    private volatile int size;

    public FilterLock(int totalThreads) {
        size = totalThreads;
        level = new int[totalThreads];
        victim = new int[totalThreads - 1];
    }

    @Override
    public void lock() {
        int me = ThreadID.get();
        for (int i = 0; i < size - 1; i++) {
            level[me] = i;
            victim[i] = me;
            //VarHandle.fullFence();
            while (sameOrHigher(me, i) && victim[i] == me) {
            }
        }
        level[me] = size - 1;
    }

    private boolean sameOrHigher(int me, int myLevel) {
        for (int id = 0; id < size; id++) {
            if (id != me && level[id] >= myLevel) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void unlock() {
        int me = ThreadID.get();
        level[me] = IDLE;
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
