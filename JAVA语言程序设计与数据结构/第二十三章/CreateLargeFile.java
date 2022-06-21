import java.io.*;

public class CreateLargeFile {
    public static void main(String[] args) throws Exception {
        DataOutputStream output = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("largedata.dat")));

        for(int i = 0; i < 2_000_000; i++)  // 放入20万个int值数据
            output.writeInt((int)(Math.random() * 1000000));
        output.close();


        // 先展示100个
        DataInputStream input = new DataInputStream(
                new BufferedInputStream(new FileInputStream("largedata.dat")));
        for(int i = 0; i < 100; i++){
            System.out.print(input.readInt() + " ");
        }
        input.close();
    }
}