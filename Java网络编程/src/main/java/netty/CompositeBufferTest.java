package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CompositeBufferTest {
    static Charset utf8 = StandardCharsets.UTF_8;

    /**
     * 通过CompositeByteBuf来复用header
     * */
    public void byteBufComposite() {
        CompositeByteBuf buf = ByteBufAllocator.DEFAULT.compositeBuffer();
        //消息头
        ByteBuf headerBuf = Unpooled.copiedBuffer("Yes:", utf8);
        //消息体1
        ByteBuf bodyBuf = Unpooled.copiedBuffer("高性能Netty", utf8);
        // 向自身增加ByteBuf对象实例
        buf.addComponents(headerBuf, bodyBuf);
        sendMsg(buf);
        //在refCnt为0前, retain
        headerBuf.retain();
        buf.release();

        buf = ByteBufAllocator.DEFAULT.compositeBuffer();
        //消息体2
        bodyBuf = Unpooled.copiedBuffer("No", utf8);
        // 向自身增加ByteBuf对象实例
        buf.addComponents(headerBuf, bodyBuf);
        sendMsg(buf);
        buf.release();
    }

    /**
     * 将CompositeByteBuf实例合并成一个新的NIO ByteBuffer缓冲区
     * */
    public void intCompositeBufComposite() {
        CompositeByteBuf buf = Unpooled.compositeBuffer(3);
        buf.addComponent(Unpooled.wrappedBuffer(new byte[]{1, 2, 3}));
        buf.addComponent(Unpooled.wrappedBuffer(new byte[]{4}));
        buf.addComponent(Unpooled.wrappedBuffer(new byte[]{5, 6}));
        //合并成一个的Java NIO缓冲区
        ByteBuffer nioBuffer = buf.nioBuffer(0, 6);
        byte[] bytes = nioBuffer.array();
        System.out.print("bytes = ");
        for (byte b : bytes) {
            System.out.print(b);
        }
        buf.release();
    }

    private void sendMsg(CompositeByteBuf buf) {
        //处理整个消息
        for (ByteBuf b :buf) {
            int length = b.readableBytes();
            byte[] array = new byte[length];
            //将CompositeByteBuf中的数据统一复制到数组中
            b.getBytes(b.readerIndex(), array);
            //处理一下数组中的数据
            System.out.print(new String(array, utf8));
        }
        System.out.println();
    }
}