package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyEchoServer {
    private int port;

    public void NettyEchoServer(int port) {
        this.port = port;
    }

    public void run(){
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();

        b.group(bossLoopGroup, workerLoopGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                .childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(NettyEchoServerHandler.INSTANCE);
            }
        });
    }

    public static void main(String[] args) throws Exception {
    }
}