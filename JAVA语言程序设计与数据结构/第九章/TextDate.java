/**/
import java.util.Date;

public class TextDate {
    public static void main(String[] args){
        Date date = new Date();
        for(int i = 0; i < 8;i++){
            long time = 10000 * (long)(Math.pow(10,i)); //返回 10 的 i 次方
            date.setTime(time); //setTime()方法用于设置此日期时间
            System.out.println(date.toString());
        }
    }
}