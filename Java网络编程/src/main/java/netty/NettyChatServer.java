package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 聊天室服务端
 */
public class NettyChatServer {
    private int port;   //端口号

    public NettyChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        // 创建bossGroup线程组: 处理连接事件
        EventLoopGroup bossGroup = null;
        // 创建workerGroup线程组: 处理读写事件
        EventLoopGroup workerGroup = null;
        try {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap = new ServerBootstrap();    // 创建服务端启动助手
            serverBootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                        .childHandler(new ChannelInitializer<SocketChannel>() {// 创建一个通道初始化对象
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                //添加编解码器
                                ch.pipeline().addLast(new StringDecoder());
                                ch.pipeline().addLast(new StringEncoder());
                                /** 向pipeline中添加自定义业务处理handler */
                                ch.pipeline().addLast(new NettyChatServerHandler());
                            }
                        });

            ChannelFuture future = serverBootstrap.bind(port); // 启动服务端，绑定端口，同时将异步改为同步
            // future-listener
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
            System.out.println("聊天室服务端启动成功.");

            future.channel().closeFuture().sync();  // 关闭通道(并不是真正意义上关闭,而是监听通道关闭的状态)和关闭连接池
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyChatServer(9998).run();
    }
}