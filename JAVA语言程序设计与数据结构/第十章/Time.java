
import java.util.Date;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(){
     this.hour = new Date().getHours();
     this.minute = new Date().getMinutes();
     this.second = new Date().getSeconds();
    }

    public Time(long elapseTime) {
        Date date = new Date(elapseTime);
        this.hour = date.getHours();
        this.minute = date.getMinutes();
        this.second = date.getSeconds();
    }

    public Time(int hour,int minute,int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public int getSecond(){
        return second;
    }

    public void setTime(long elapseTime){
        long seconds = elapseTime / 1000;
        this.second = (int)seconds % 60;
        seconds = seconds / 60;
        this.minute = (int)second % 60;
        seconds = seconds / 60;
        this.hour = (int)second % 24;
    }

    public static void main(String[] args){
        Time time = new Time();
        System.out.println(time.hour);
        System.out.println(time.minute);
        System.out.println(time.second);

        System.out.println(" ");
        time.setTime(555550000);
        System.out.println(time.hour);
        System.out.println(time.minute);
        System.out.println(time.second);
    }
}