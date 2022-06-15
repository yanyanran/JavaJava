import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyThreadPool{
    private static BlockingQueueWithLock<Runnable> queue = null;
    private volatile int coreSize;
    private volatile int poolSize;  //当前线程数
    private volatile int maxSize;
    private volatile int timeOut;

    public MyThreadPool(int corePoolSize, int maxPoolSize, int timeout) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        int num = ((ThreadPoolExecutor)executor).getActiveCount();
        this.coreSize = corePoolSize;
        this.maxSize = maxPoolSize;
        this.timeOut = timeout;

    }

    public void execute(Runnable task){

    }


}