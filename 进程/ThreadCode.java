import java.util.concurrent.TimeUnit;

public class ThreadCode {
    public static void main(String[] args) throws InterruptedException{
        // 添加钩子线程, 用来监听 JVM 退出
        Runtime.getRuntime().addShutdownHook(new Thread(()->System.out.println("The JVM exit success!!")));

        // 主线程中new一个非守护线程
        Thread t = new Thread(()->{
            while(true) {
                try{
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("still running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 标记为守护线程
        t.setDaemon(true);
        t.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The main thread ready to exit...");
    }
}
