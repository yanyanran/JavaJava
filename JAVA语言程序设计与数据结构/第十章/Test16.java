import java.util.List;
import java.util.Random;

public class Test16 {
    public static void main(String[] args){
        Integer num[] = new Integer[100];
        Test16 divide = new Test16();
        System.out.println("原数组为：");
        divide.List(num);
        System.out.println(" ");
        System.out.println("符合要求的数组为：");
        divide.Find(num);

    }

    // 生成一个有50个十进制数的数组
    public Integer[] List(Integer[] num){
        Random random = new Random();
        for(int i = 0; i < num.length; i++){
            num[i] = random.nextInt(1000);
            System.out.println(num[i] + " ");
        }
        return num;
    }

    //List<Integer>指的是存int类型数据的列表
    public Integer[] Find(Integer[] num){
        Integer[] newNum = new Integer[100];
        for(int i = 0,j = 0; i < num.length ; i++){
            if(num[i] % 2 == 0 || num[i] % 3 == 0){
                newNum[j] = num[i];
                System.out.println(newNum[j] + " ");
                j++;
                if(j > 9){
                    break;
                }else continue;
            }
        }
        return newNum;
    }
}