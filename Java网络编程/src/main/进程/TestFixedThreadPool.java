import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFixedThreadPool {
    /**
     * 固定大小的线程池：
     * 同时可以处理【参数】个任务，多余的任务会排队，当处理完一个马上就会去接着处理排队中的任务。
    */
 private  static void fixedThreadPool(){
        long startTime = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(2);

        for(int i =1; i <= 15; i++){
            es.submit(new MyThread(i,es));
        }

        // 结束所有线程
        //es.shutdown();
        CreateThread createThread = new CreateThread(es);
        new Thread(createThread).start();
        System.out.println("***********************");
        while(true){
            try{
                Thread.sleep(500);
                System.out.println("all -- " + es.toString());
                if(es.isTerminated()){
                    System.out.println("last --" + es.toString());
                    long end = System.currentTimeMillis();
                    System.out.println("线程执行用时:" + (end - startTime)+"ms");
                    break;
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    // Main
    public static void main(String[] args){
        fixedThreadPool();
    }

    // MyThread
    static class MyThread implements Runnable{
        int id;
        ExecutorService exe;
        MyThread(int id, ExecutorService exe){
            this.id = id;
            this.exe = exe;
        }
        public void run(){
            System.out.println(id + "- start");  // start
            try{
                Thread.sleep(1000L);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(id + "- end");  // end
            System.out.println(id + "- exe info: " + exe.toString());
        }
    }

    // CreateThread
    static class CreateThread implements Runnable{
        ExecutorService exe;
        CreateThread(ExecutorService exe){
            this.exe = exe;
        }
        public void run(){
            try{
                int n = 100;
                while(true){
                    Thread.sleep(5000L);
                    for(int i = 1; i <= 15;i++){
                        exe.submit(new MyThread(n + i,exe));
                    }
                    n++;
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}