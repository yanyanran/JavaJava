public class MyThreadPool {
    private static BlockingQueueWithLock<Runnable> queue = null;
    private volatile int coreSize;
    private volatile int maxSize;
    private volatile int timeOut;

    public MyThreadPool(int corePoolSize, int maxPoolSize, int timeout) {
        this.coreSize = corePoolSize;
        this.maxSize = maxPoolSize;
        this.timeOut = timeout;
    }

    public void execute(Runnable task){

    }

    public BlockingQueueWithLock shutdown(){

    }
}