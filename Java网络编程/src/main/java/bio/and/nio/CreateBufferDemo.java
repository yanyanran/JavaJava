package bio.and.nio;

import java.nio.ByteBuffer;

/**
 * 创建缓冲区
 */
public class CreateBufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);   // 创建一个指定长度5的缓冲区
        for (int i = 0; i < 5; i++) {
            System.out.println(byteBuffer.get());
        }
            //在此调用会报错BufferUnderflowException(读缓冲区)
            //System.out.println(byteBuffer.get());
        ByteBuffer wrap = ByteBuffer.wrap("lagou".getBytes());   // 创建一个有内容的缓冲区
        for (int i = 0; i < 5; i++) {
            System.out.println(wrap.get());
        }
    }
}