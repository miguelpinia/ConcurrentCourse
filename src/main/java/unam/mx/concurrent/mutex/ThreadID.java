package unam.mx.concurrent.mutex;

/**
 *
 * @author miguel
 */
public class ThreadID {

    private static volatile int nextID = 0;

    private static final ThreadLocalID threadID = new ThreadLocalID();

    public static int get() {
        return threadID.get();
    }

    public static void reset() {
        nextID = 0;
    }

    private static class ThreadLocalID extends ThreadLocal<Integer> {

        @Override
        protected synchronized Integer initialValue() {
            return nextID++;
        }
    }

}
