package netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.AbstractChannel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.CharsetUtil;

public class ClientFutureListener {
    public static void main(String[] args) {
        AbstractChannel ctx = ;
        ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer("你好 呀,我是Netty客户端", CharsetUtil.UTF_8));
                channelFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("数据发送成功.");
                        } else {
                            System.out.println("数据发送失败.");
                        }
                    }
                });
    }
}