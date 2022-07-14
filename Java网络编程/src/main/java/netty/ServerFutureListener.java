package netty;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ServerFutureListener {
    public static void main(String[] args){
        AbstractBootstrap bootstrap = null;
        ChannelFuture future = bootstrap.bind(9999);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功!");
                } else {
                    System.out.println("端口绑定失败!");
                }
            }
        });
        
    }
}