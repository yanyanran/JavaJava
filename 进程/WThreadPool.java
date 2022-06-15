import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class WThreadPool {
        private volatile boolean RUNNING = true;// 是否正在运行
        private final ReentrantLock lock = new ReentrantLock();// 并发锁
        private final HashSet<Worker> workers = new HashSet<>();// 不重复的工作集
        private static BlockingQueue<Runnable> queue = null;// 任务阻塞队列
        private final ArrayList<Thread> threads = new ArrayList<>();// 线程工厂
        private volatile int poolSize;// 线程池的核心线程数
        private volatile int coreSize;// 当前线程池中的线程数
        private volatile boolean shutdown = false;// 是否停止工作

        public WThreadPool(int poolSize) {
            this.poolSize = poolSize;
            queue = new ArrayBlockingQueue<>(poolSize);
        }

        public void execute(Runnable command) {
            if (command == null) {
                throw new NullPointerException();
            }

            if (coreSize < poolSize) {
                addThread(command);
            } else {
                try {
                    queue.put(command);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }

        private void addThread(Runnable task) {
            lock.lock();
            try {
                coreSize++;
                Worker worker = new Worker(task);
                workers.add(worker);
                Thread thread = new Thread(worker);
                threads.add(thread);
                thread.start();
            } finally {
                lock.unlock();
            }
        }

        public void shutdown() {
            RUNNING = false;
            if (!workers.isEmpty()) {
                for (Worker worker : workers) {
                    worker.interruptIfIdle();
                }
            }
            shutdown = true;
            Thread.currentThread().interrupt();
        }

        private final class Worker implements Runnable {
            public Worker(Runnable task) {
                queue.offer(task);
            }

            public Runnable getTask() throws InterruptedException {
                return queue.take();
            }

            @Override
            public void run() {
                while (true && RUNNING) {
                    if (shutdown) {
                        Thread.interrupted();
                    }
                    Runnable task = null;
                    try {
                        task = getTask();
                        task.run();
                    } catch (InterruptedException e) {
                    }
                }
            }

            public void interruptIfIdle() {
                for (Thread thread : threads) {
                    System.out.println(thread.getName() + " interrupt");
                    thread.interrupt();
                }
            }
        }

        public static void main(String[] args) {
            WThreadPool executor = new  WThreadPool(6);
            for (int i = 0; i < 6; i++) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("线程" + Thread.currentThread().getName() + "在工作....");
                    }
                });
            }
            executor.shutdown();
        }//直接实现在线程内部进行测试
}
