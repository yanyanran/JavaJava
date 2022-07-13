import java.nio.Buffer;
import java.nio.ByteBuffer;

public abstract class TestByteBuffer extends Buffer implements Comparable<ByteBuffer> {
    final byte[] nb;
    private final int offset;

    TestByteBuffer (int mark, int pos, int lin,int cap, byte[] nb, int offset) {
        super(mark, pos, lin, cap);
        this.nb = nb;
        this.offset = offset;
    }
    public static void main(String[] args) throws Exception {
        String msg = "java技术爱好者，起飞！";
        //创建一个固定大小的buffer(返回的是HeapByteBuffer)
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = msg.getBytes();
        byteBuffer.put(bytes);  //写入数据到Buffer中
        byteBuffer.flip();      //切换成读模式(关键一步
        byte[] tempByte = new byte[bytes.length];   //创建一个临时数用于存储获取到的数据
        int i = 0;

        while (byteBuffer.hasRemaining()) {   //如果还有数据，就循环
            byte b = byteBuffer.get();   //获取byteBuffer中的数据
            tempByte[i] = b;     //放到临时数组中
            i++;
        }
        System.out.println(new String(tempByte));//打印结果:java技术爱好者，起飞！
    }
}