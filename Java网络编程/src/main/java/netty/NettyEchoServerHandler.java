package netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

import java.util.logging.Logger;

public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {
    public static NettyEchoServerHandler INSTANCE = new NettyEchoServerHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;

        // create Logger -> 解决"无法从 static 上下文引用非 static 方法 'info(java.lang.String)'"问题
        Logger logger = Logger.getLogger(NettyEchoServerHandler.class.getName());

        logger.info("msg type: " + (in.hasArray()? "堆内存" : "直接内存"));
        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0, arr);
        logger.info("写回前，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        ChannelFuture f =ctx.writeAndFlush(msg);
        f.addListener((ChannelFutureListener)->{
            logger.info("写回后，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        });
    }
}