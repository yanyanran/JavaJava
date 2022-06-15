import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool{
    private final ReentrantLock lock = new ReentrantLock();// ReentrantLock锁
    private static BlockingQueueWithLock<Runnable> queue = null;// 任务阻塞队列
    private final ArrayList<Thread> threads = new ArrayList<>();// 线程工厂
    private final HashSet<MyThreadPool.Running> workers = new HashSet<>();// 不重复的工作集

    private volatile int coreSize;  // 核心线程数(min)
    private volatile int poolSize;  // 当前运行线程数
    private volatile int maxSize;  // 最大线程数(max)
    private volatile int timeOut;
    private volatile boolean RUNNING = true;// 是否正在运行
    private volatile boolean shutdown = false;// 是否停止工作

    //MyThreadPool
    public MyThreadPool(int corePoolSize, int maxPoolSize, int timeout) {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        int num = ((ThreadPoolExecutor)ex).getActiveCount();  // 获取当前运行线程数

        this.coreSize = corePoolSize;
        this.maxSize = maxPoolSize;
        this.timeOut = timeout;
        this.poolSize = num;

        queue = (BlockingQueueWithLock<Runnable>) (new ArrayBlockingQueue<Runnable>(poolSize)); // 不强转会报不兼容 但已经继承了ArrayBlockingQueue了(?)
    }

    //execute
    public void execute(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        if (poolSize < maxSize) {
            addThread(task);
        } else {
            try {
                queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //shutdown
    public void shutdown() {
        RUNNING = false;
        if (!workers.isEmpty()) {
            for (MyThreadPool.Running worker : workers) {
                for (Thread thread : threads) {
                    System.out.println(thread.getName() + " interrupt");
                    thread.interrupt();
                }
            }
        }
        shutdown = true;
        Thread.currentThread().interrupt();
    }

    private void addThread(Runnable task) {
        lock.lock();
        try {
            poolSize++;
            MyThreadPool.Running worker = new MyThreadPool.Running(task);
            workers.add(worker);
            Thread thread = new Thread(worker);
            threads.add(thread);
            thread.start();
        } finally {
            lock.unlock();
        }
    }

    private final class Running implements Runnable {
        public Running(Runnable task) {
            queue.offer(task);  // 队尾添加
        }

        @Override
        public void run() {
            while (true && RUNNING) {
                if (shutdown) {
                    Thread.interrupted();
                }
                Runnable task = null;
                try {
                    task = queue.take();
                    task.run();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    //Main
    public static void main(String[] args) {
        MyThreadPool ex = new  MyThreadPool(2,5,0);
        for (int i = 2; i < 5; i++) {
            ex.execute(new Runnable() {
                public void run() {
                    System.out.println("Thread" + Thread.currentThread().getName() + "still working!\n");
                }
            });
        }
        ex.shutdown();
    }
}