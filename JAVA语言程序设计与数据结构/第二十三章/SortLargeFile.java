import java.io.*;

public class SortLargeFile {
    public static final int MAX_ARRAY_SIZE = 100000;
    public static final int BUFFER_SIZE = 100000;

    public static void main(String[] args) throws Exception {
        // 从largedata.dat中读取数据，排好序后写入sortedfile.dat中
        sort("largedata.dat", "sortedfile.dat");
        // 显示指定文件的前100个数据
        displayFile("sortedfile.dat");
    }

    /**
     * 排序
     */
    public static void sort(String sourcefile, String targetfile) throws Exception{
        // 创建初始数据，然后将排好序的分段存入f1.dat
        int numberOfSegments = initializeSegments(MAX_ARRAY_SIZE, sourcefile, "f1.dat");

        // 在targetfile中产生一个有序文件
        merge(numberOfSegments,MAX_ARRAY_SIZE,"f1.dat","f2.dat","f3.dat",targetfile);
    }

    /**
     * 1、分段排序
     * 创建初始有序分段
     */
    public static int initializeSegments(int segmentSize, String originalFile, String f1) throws Exception {
        int[] list = new int[segmentSize];  // 创建具有最大尺寸的数组
        // 为原始文件创建一个数据输入流
        DataInputStream input = new DataInputStream(
                new BufferedInputStream(new FileInputStream(originalFile)));
        // 为原始文件创建一个数据输出流
        DataOutputStream output = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f1)));

        int numberOfSegments = 0;
        while(input.available() > 0){
            numberOfSegments++;
            int i = 0;
            // 从文件中读取一段数据到数组中
            for( ; input.available() > 0 && i < segmentSize; i++){
                list[i] = input.readInt();
            }
            java.util.Arrays.sort(list, 0 , 1);  // 排序

            for(int j = 0; i < i; j++){
                output.writeInt(list[j]);  // 将数组数据写入临时文件中
            }
        }
        input.close();
        output.close();

        return numberOfSegments;  // 返回分段个数
    }

    /**
     * 归并函数
     */
    // f2辅助f1归并到f3
    public static void merge(int numberOfSegments,int segmentSize,String f1,String f2,String f3,String targetfile) throws Exception{
        if(numberOfSegments > 1){
            mergeOneStep(numberOfSegments,segmentSize,f1,f2,f3);
            // 调用新归并（每归并一次，numberOfSegments减半，有序分段大小segmentSize大小翻倍）
            merge((numberOfSegments + 1) / 2, segmentSize * 2, f3, f1, f2,targetfile);
        }else {       // numberOfSegment = 1时，结束merge递归
            File sortedFile = new File(targetfile);
            if(sortedFile.exists()){
                sortedFile.delete();
            }
            // 这时排好的数据在f1中
            new File(f1).renameTo(sortedFile);  // 重命名为sortedFile
        }
    }

    /**
     * 2、迭代归并
     */
    public static void mergeOneStep(int numberOfSegments, int segmentSize, String f1,String f2,String f3) throws Exception{
        DataInputStream f1Input = new DataInputStream(
                new BufferedInputStream(new FileInputStream(f1), BUFFER_SIZE));
        DataOutputStream f2Output = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f2), BUFFER_SIZE));

        // 从f1拷贝一半的段到f2
        copyHalfTof2(numberOfSegments,segmentSize,f1Input,f2Output);
        f2Output.close();

        // 将f1中剩余的数据段与f2中的数据段一起合并到f3中
        DataInputStream f2Input = new DataInputStream(new BufferedInputStream(new FileInputStream(f2),BUFFER_SIZE));
        DataOutputStream f3Output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f3), BUFFER_SIZE));

        mergeSegments(numberOfSegments / 2, segmentSize, f1Input,f2Input,f3Output);

        f1Input.close();
        f2Input.close();
        f3Output.close();
    }

    /**
     * 复制f1中前半部分至f2
     */
    private static void copyHalfTof2(int numberOfSegments, int segmentSize, DataInputStream f1, DataOutputStream f2) throws Exception{
        for(int i = 0; i < (numberOfSegments / 2) * segmentSize; i++){
            f2.writeInt(f1.readInt());  // 将一个 int 值作为四个字节写入底层流
        }
    }

    /**
     * 归并所有分段
     */
    private static void mergeSegments(int numberOfSegments,int segmentSize, DataInputStream f1, DataInputStream f2, DataOutputStream f3) throws Exception{
        for(int i = 0; i < numberOfSegments; i++){
            mergeTwoSegments(segmentSize, f1, f2, f3);    // 归并两个片段
        }

        while (f1.available() > 0){
            f3.writeInt(f1.readInt());
        }
    }

    /**
     * 归并两个片段
     */
    private static void mergeTwoSegments(int segmentSize, DataInputStream f1, DataInputStream f2, DataOutputStream f3) throws Exception{
        int intFormF1 = f1.readInt();
        int intFormF2 = f2.readInt();
        int f1Count = 1;
        int f2Count = 1;

        // available() 方法可以在读写操作前先得知数据流里有多少个字节可以读取
        while(true){
            // 判断谁的字节数少，就从谁先开始
            if(intFormF1 < intFormF2){  // f1少
                f3.writeInt(intFormF1);
                if(f1.available() == 0 || f1Count++ >= segmentSize){
                    f3.writeInt(intFormF2);
                    break;
                }else {
                    intFormF1 = f1.readInt();
                }
            }else{  // f2少
                f3.writeInt(intFormF2);
                if(f2.available() == 0 || f2Count++ >= segmentSize){
                    f3.writeInt(intFormF1);
                    break;
                }else{
                    intFormF2 = f2.readInt();
                }
            }
        }

        // 如果还有多出的数据，直接写入f3
        while(f1.available() > 0 && f1Count++ < segmentSize){
            f3.writeInt(f1.readInt());
        }
        while(f2.available() > 0 && f2Count++ < segmentSize){
            f3.writeInt(f2.readInt());
        }
    }

    /**
     * 显示指定文件的前100个数据
     */
    public static void displayFile(String filename){
        try{
            DataInputStream input = new DataInputStream(new FileInputStream(filename));
            for(int i = 0; i < 100; i++) {
                System.out.print(input.readInt() + " ");
            }
            input.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}