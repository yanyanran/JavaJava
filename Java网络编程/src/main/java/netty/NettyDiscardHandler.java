package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class NettyDiscardHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println("Received and Delete");
            while (in.isReadable()) {
                System.out.print((char)in.readByte());
            }
        }finally{
            ReferenceCountUtil.release(msg);
        }
    }
}

/*
获取/HTTP/1.1
主机：本地主机：8080
连接：保持活动
缓存控制：max-age=0
sec-ch-ua: " 非品牌;v="99", "Chromium";v="99", "谷歌浏览器";v="99"
sec-ch-ua-mobile: ?0
sec-ch-ua-平台：“Linux”
升级不安全请求：1
用户代理：Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.84 Safari/537.36
接受：text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,**q=0.8,application/signed-exchange;v=b3; q=0.9
Sec-Fetch-Site：无
Sec-Fetch-Mode：导航
Sec-Fetch-User：?1
Sec-Fetch-Dest：文档
接受编码：gzip、deflate、br
接受语言：zh-CN,zh;q=0.9
Cookie：idea-4c1f8b3d=3939cdc0-d067-4f8b-ab58-b63200af969e
* */
