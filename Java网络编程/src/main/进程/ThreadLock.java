public class ThreadLock {
    public static void main(String[] args) throws Exception {
        var add = new AddThread();
        var edc = new EdcThread();
        add.start();
        edc.start();
        add.join();
        edc.join();
        System.out.println(Counter.count);
    }
}

class Counter{
    public static final Object lock = new Object(); //w lock
    public static int count = 0;
}

class AddThread extends Thread {
    public void run(){
        for(int i = 0; i < 1000;i++){
            synchronized(Counter.lock){
                Counter.count += 1;
            }
        }
    }
}

class EdcThread extends Thread {
    public void run() {
        for(int i = 0; i < 1000;i++){
            synchronized(Counter.lock){
                Counter.count -= 1;
            }
        }
    }
}