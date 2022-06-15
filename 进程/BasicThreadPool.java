import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

interface ThreadPool {
    void execute(Runnable runnable);
    boolean isShutDown();
    void shutDown();
    // 获取线程池的初始化大小
    int getInitSize();
    // 获取线程池最大的线程数
    int getMaxSize();
    // 获取线程池的核心线程数量
    int getCoreSize();
    // 获取线程池用于缓存任务队列的大小
    int getQueueSize();
    // 获取线程池中活跃线程的数量
    int getActiveCount();
}
@FunctionalInterface
interface ThreadFactory {
    Thread createThread(Runnable runnable);
}

interface RunnableQueue {
    void offer(Runnable runnable);
    Runnable take();
    int size();
}

interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    // 该拒绝策略会直接将任务丢弃
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            System.out.println(runnable + " 任务已被丢弃。");
        }
    }

    // 该拒绝策略会向任务提交者抛出异常
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("任务 " + runnable + " 将被终止。");
        }
    }

    // 该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutDown())
                runnable.run();
        }
    }

}

class RunnableDenyException extends RuntimeException{

    public RunnableDenyException(String message) {
        super(message);
    }

}

class LinkedRunnableQueue implements RunnableQueue {
    //任务队列的限制
    private final int limit;
    //拒绝策略
    private final DenyPolicy denyPolicy;
    //存放任务的容器
    private final LinkedList<Runnable> linkedList=new LinkedList<>();
    private final ThreadPool threadPool;
    public LinkedRunnableQueue(int limit,DenyPolicy denyPolicy,ThreadPool threadPool) {
        this.limit=limit;
        this.denyPolicy=denyPolicy;
        this.threadPool=threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (linkedList) {
            //若果任务队列满了，就采用阻塞策略
            if(linkedList.size()>limit){
                denyPolicy.reject(runnable, threadPool);
            }
            else {
                linkedList.addLast(runnable);
                linkedList.notifyAll();
            }
        }

    }

    @Override
    public Runnable take() {
        synchronized (linkedList) {
            while (linkedList.isEmpty()) {
                try {
                    linkedList.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return linkedList.remove();
        }

    }

    @Override
    public int size() {
        synchronized(linkedList){
            return linkedList.size();
        }

    }
}

class InternalTask implements Runnable {
    private final RunnableQueue runnableQueue;
    //关闭标记
    private volatile boolean isStop=false;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        //若当前线程未被中断且标记位未终止则继续从任务队列中取任务在本线程中进行工作
        while(!isStop&&!Thread.currentThread().isInterrupted()){
            Runnable runnable = runnableQueue.take();
            runnable.run();
        }
    }

    public void stop(){
        this.isStop=true;
    }

}

public class BasicThreadPool extends Thread implements ThreadPool{
    private final int initSize;
    private final int coreSize;
    private  int activeCount;
    private final int maxSize;
    private volatile boolean isShutdown=false;
    private final TimeUnit timeUnit;
    private final long keepAliveTime;
    //创建线程池的工厂
    private ThreadFactory threadFactory;
    private final static ThreadFactory DEFAULT_THREAD_FACTORY=new DefaultThreadFactory();
    private final RunnableQueue runnableQueue;

    private static class DefaultThreadFactory implements ThreadFactory{
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(0);
        private static final ThreadGroup GROUP = new ThreadGroup("MyThreadGroupPool-"
                + GROUP_COUNTER.getAndIncrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);
        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(GROUP, runnable,  "thread-pool-" + COUNTER.getAndIncrement());
        }
    }

    //默认的拒绝策略
    private final static DenyPolicy DEFAULT_DENY_POLICY=new DenyPolicy.DiscardDenyPolicy();

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, coreSize, queueSize, maxSize, TimeUnit.SECONDS, 10, DEFAULT_DENY_POLICY, DEFAULT_THREAD_FACTORY);
    }

    public BasicThreadPool(int initSize, int coreSize, int queueSize, int maxSize, TimeUnit timeUnit, long keepAliveTime, DenyPolicy denyPolicy,ThreadFactory threadFactory) {
        this.initSize = initSize;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.timeUnit = timeUnit;
        this.keepAliveTime = keepAliveTime;
        this.threadFactory = threadFactory;
        this.runnableQueue=new LinkedRunnableQueue(queueSize, denyPolicy, this);
        init();
    }

    private final Queue<ThreadTask> threadTaskQueue=new LinkedList<>();

    private static class ThreadTask{
        private Thread thread;
        private InternalTask internalTask;
        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private void init(){
        //第一步开启线程池维护
        this.start();
        //第二步创建线程放到池子里存起来
        for(int i=0;i<initSize;i++){
            newThread();
        }
    }

    private void newThread(){
        InternalTask internalTask = new InternalTask(runnableQueue);
        //创造执行内部任务的工作线程
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadTaskQueue.offer(threadTask);
        this.activeCount++;
        //启动工作线程
        thread.start();
    }

    public void removeThread(){
        ThreadTask threadTask = threadTaskQueue.remove();
        threadTask.internalTask.stop();
        //threadTask.thread.interrupt();
        //有效存活的线程数量减一
        this.activeCount--;
    }

    @Override
    public void run() {
        while (!isShutdown && !Thread.currentThread().isInterrupted()) {
            try {
                //先让工作线程存活到有效时间
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            synchronized(this){
                if(isShutdown)
                    break;
                //第一种情况  如果任务队列不为空，而且线程存活数量小于核心数量，则扩容，说明当前工作线程较少
                if(runnableQueue.size()>0&&activeCount<coreSize){
                    for(int i=initSize;i<coreSize;i++){
                        newThread();
                    }
                    //继续让新创建的线程在有效时间内存活
                    continue;
                }
                //第二种情况 如果任务队列不为空，而且线程存活数量大于核心数但是小于最大工作线程数量，则继续扩容，增加工作线程数量提高性能
                if(runnableQueue.size()>0&&activeCount<maxSize){
                    for(int i=coreSize;i<maxSize;i++){
                        newThread();
                    }
                }
                //若任务队列为空，而且存活线程大于核心数，说明任务线程充足，下一步需要回收线程了
                if(runnableQueue.size()==0&&activeCount>coreSize){
                    for (int i = coreSize; i <= activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }
    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown)
            throw new IllegalStateException("线程池已销毁。");
        // 提交任务
        this.runnableQueue.offer(runnable);
    }

    @Override
    public boolean isShutDown() {
        if (this.isShutdown)
            throw new IllegalStateException("线程池已销毁。");
        return this.isShutdown;
    }

    @Override
    public void shutDown() {
        synchronized (this) {
            if(isShutdown)
                return;
            //设置关闭标记位置
            this.isShutdown=true;
            //关闭池中的所有工作线程
            for(ThreadTask threadTask:threadTaskQueue){
                //设置内部任务的中止标记位
                threadTask.internalTask.stop();
                //中断任务线程
                threadTask.thread.interrupt();
            }
            //最后中断当前线程
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (this.isShutdown)
            throw new IllegalStateException("线程池已销毁。");
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (this.isShutdown)
            throw new IllegalStateException("线程池已销毁。");
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (this.isShutdown)
            throw new IllegalStateException("线程池已销毁。");
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (this.isShutdown)
            throw new IllegalStateException("线程池已销毁。");
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if (this.isShutdown)
            throw new IllegalStateException("线程池已销毁。");
        return this.activeCount;
    }

}

class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        // 定义线程池，初始化线程数为 2，最大线程数为 6，核心线程数为 4，任务队列最多允许 1000 个任务
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        // 定义 20 个任务并且提交给线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " 正在运行。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 不断输出线程池的信息
        while (true) {
            System.out.println("threadPool.getActiveCount() = " + threadPool.getActiveCount());
            System.out.println("threadPool.getQueueSize() = " + threadPool.getQueueSize());
            System.out.println("threadPool.getCoreSize() = " + threadPool.getCoreSize());
            System.out.println("threadPool.getMaxSize() = " + threadPool.getMaxSize());
            System.out.println("-----------------------------------------------------------");
            TimeUnit.SECONDS.sleep(5);
            threadPool.shutDown();
        }
    }
}