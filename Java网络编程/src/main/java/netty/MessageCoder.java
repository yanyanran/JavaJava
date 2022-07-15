package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.CharsetUtil;

import java.util.List;

public class MessageCoder extends MessageToMessageCodec {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("running encoding...");
        String str =(String)msg;
        out.add(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
    }
    
    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        System.out.println("running decoding...");
        ByteBuf bytebuf = (ByteBuf)msg;
        out.add(bytebuf.toString(CharsetUtil.UTF_8));
    }
}