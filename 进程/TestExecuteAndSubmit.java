import java.util.concurrent.*;

public class TestExecuteAndSubmit {
        static class Customer extends Thread {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始执行");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        static class Home implements Callable {
            int start;
            int end;

            public Home(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                20,
                20,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new ThreadFactory() {
            @Override
            public Thread createThread(Runnable runnable) {
                return null;
            }

            @Override
            public Thread newThread(Runnable r) {
                //1、设置线程的名字
                Thread thread = new Thread(r);
                //thread.setName("通过自定义线程工厂谁知线程名字");
                //2、设置成守护线程
                //thread.setDaemon(true);
                return thread;
            }
        },
                //四种阻塞队列满之后的处理方式
//                new ThreadPoolExecutor.CallerRunsPolicy()); 使用调用线程处理，比如是main调用的线程池，用main线程执行
//                new ThreadPoolExecutor.DiscardOldestPolicy());把阻塞队列首元素丢掉，执行当前线程
//                new ThreadPoolExecutor.DiscardPolicy());直接丢掉当前线程
                new ThreadPoolExecutor.AbortPolicy());//跑出异常

        executor.execute(new Customer());
        //计算1加到30
        Future submit1 = executor.submit(new Home(1,10));
        //任务是否执行完成false
        System.out.println(submit1.isDone());
        Thread.sleep(100);
        //任务是否执行完成true
        System.out.println(submit1.isDone());
        Future submit2 = executor.submit(new Home(11,20));
        Future submit3 = executor.submit(new Home(21,30));
        ((Integer)submit1.get()).intValue();
        System.out.println((((Integer)submit1.get()).intValue()+((Integer)submit2.get()).intValue()+((Integer)submit3.get()).intValue()));
    }
}