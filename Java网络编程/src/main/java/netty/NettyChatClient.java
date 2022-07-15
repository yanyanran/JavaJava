package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * 聊天室的客户端
 */
public class NettyChatClient {
    private String ip;  //服务端IP
    private int port;   //服务端端口号

    public NettyChatClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public NettyChatClient() {

    }

    public void run() throws InterruptedException {
        // 创建线程组
        EventLoopGroup group = null;
        try {
            group = new NioEventLoopGroup();

            Bootstrap bootstrap = new Bootstrap();  // 创建客户端启动助手
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() { // 创建一个通道初始化对象
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //编解码器
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            // 添加自定义业务处理handler
                            ch.pipeline().addLast(new NettyChatClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();   // 启动客户端,等待连接服务端,同时将异步改为同步

            Channel channel = channelFuture.channel();
            System.out.println("-------" + channel.localAddress().toString().substring(1) +"--------");

            // input
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                //向服务端发送消息
                channel.writeAndFlush(msg);
            }
            channelFuture.channel().closeFuture().sync();   // 关闭通道和关闭连接池
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new NettyChatClient("127.0.0.1", 9998).run();
    }
}