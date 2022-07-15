package netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class MessageEncoder extends MessageToMessageEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext ctc, String msg, List<Object> out) throws Exception {
        System.out.println("running encoding...");
        out.add(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }
}