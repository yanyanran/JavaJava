package thread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestThread extends Thread{
    private String filename;

    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    // 为指定文件计算一个256位的SHA-2信息摘要
    public void run(){
        try{
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            // 使用指定的输入流和消息摘要，创建摘要输入流（读取）
            // 此过滤器流在读取文件时会计算一个 加密散列函数
            DigestInputStream din = new DigestInputStream(in, sha);
            while(din.read() != -1);
            din.close();
            byte[] digest = sha.digest();  // 通过digest()得到散列

            StringBuilder result = new StringBuilder(filename);
            result.append(": ");
            // DatatypeConverter.printHexBinary(byte[] val) 将字节数组转换为字符串
            // result.append(DatatypeConverter.printHexBinary(digest));
            System.out.println(result);
        }catch(NoSuchAlgorithmException e){
            System.err.println(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for(String filename : args) {
            Thread t = new DigestThread(filename);
            t.start();
        }
    }
}