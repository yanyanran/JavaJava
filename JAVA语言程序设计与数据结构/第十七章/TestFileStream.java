import java.io.*;

public class TestFileStream {
    public static void main(String[] args) throws IOException{
        try(
                FileOutputStream output = new FileOutputStream("temp.dat");
        ){
            for(int i = 1; i <= 10; i++){
                output.write(i); //写入
            }
        }

        try(
                FileInputStream input = new FileInputStream("temp.dat");
        ){
            int value;
            while((value = input.read()) != -1){
                //通过input.read()读取一个字节检验是否为-1。-1时代表文件结束
                System.out.println(value + " ");
            }
        }
    }
}