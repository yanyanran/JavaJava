package netty;

import com.twelvemonkeys.lang.DateUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.logging.Logger;

public class NettyEchoClient {
    private final int serverPort;
    private final String serverIP;
    Bootstrap b = new Bootstrap();

    public NettyEchoClient(String ip, int port) {
        this.serverPort = port;
        this.serverIP = ip;
    }

    public void runClient() {
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        Logger logger = Logger.getLogger(NettyEchoClient.class.getName());
        try {
            b.group(workerLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(serverIP, serverPort)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(NettyEchoServerHandler.INSTANCE);
                }
            });
            ChannelFuture f = b.connect();
            f.addListener((ChannelFutureListener)-> {
                if(ChannelFutureListener.isSuccess()) {
                    logger.info("EchoClient connected success!");
                }else {
                    logger.info("EchoClient connected fail");
                }
            });
            f.sync();
            Channel channel = f.channel();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please import what you want to send: ");
            while(scanner.hasNext()) {
                String next = scanner.next();
                // byte[] bytes = (DateUtil.getNow() + ">>" + next).getBytes("UTF-8");
                ByteBuf buffer = channel.alloc().buffer();
                //buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                System.out.println("Please import what you want to send: ");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            workerLoopGroup.shutdownGracefully();
        }
    }
}