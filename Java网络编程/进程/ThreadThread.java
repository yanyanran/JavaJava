public class ThreadThread {
    public static void main(String[] args){
        send s = new send();
        s.start();
        receive r = new receive(s);
        r.start();
    }
}

class send extends Thread {
    int r = 0;
    public void run(){
        while (r < 3){
            try {
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx");
                r++;
                sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("1111111111");
    }
}

class receive extends Thread {
    private Thread subThread;
    public receive(Thread thread) {
        subThread = thread;
    }
    public void run() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("关闭");
    }
}