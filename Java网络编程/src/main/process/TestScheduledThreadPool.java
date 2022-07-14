import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestScheduledThreadPool {
    //格式化
    static SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //AtomicInteger用来计数
    static AtomicInteger number = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 3; i++){
            executorService.scheduleAtFixedRate(new Runnable(){
                @Override
                public void run(){
                    System.out.println("第" + number.incrementAndGet() + "周期线程运行当前时间【" + sim.format(new Date()) + "】");
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }, 2, 5,TimeUnit.SECONDS);
        }
        System.out.println("主线程运行当前时间【" + sim.format(new Date()) + "】");
    }
}