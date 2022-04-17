package com.company;

class StopWatch {
    private long startTime;
    private long endTime;
    StopWatch(){
        startTime = System.currentTimeMillis();
    }
    public void start(){
        startTime = System.currentTimeMillis();
    }
    public void stop(){
        endTime = System.currentTimeMillis();
    }
    public long getElapsedTime(){
        return endTime - startTime;
    }
}

class Hanoi {
    /**
     *
     * @param n 盘子的数目
     * @param origin 源座
     * @param assist 辅助座
     * @param destination 目的座
     */
    public void hanoi(int n, char origin, char assist, char destination) {
        if (n == 1) {
            move(origin, destination);
        } else {
            hanoi(n - 1, origin, destination, assist);
            move(origin, destination);
            hanoi(n - 1, assist, origin, destination);
        }
    }
    private void move(char origin, char destination) {
        System.out.println("Direction:" + origin + "--->" + destination);
    }
}

public class TextStopWatch {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Hanoi hanoi = new Hanoi();
        hanoi.hanoi(20, 'A', 'B', 'C');
        stopWatch.stop();
        System.out.println(stopWatch.getElapsedTime());
    }
}

