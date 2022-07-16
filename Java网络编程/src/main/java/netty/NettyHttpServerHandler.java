package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * HTTP 服务器处理类
 * SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter 子类
 * HttpObject 指的是服务器端与客户端处理数据时的数据类型
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断 msg 参数是否是 Http 请求
        if(msg instanceof HttpRequest){
            System.out.println(ctx.channel().remoteAddress() + " 客户端请求数据 ... ");
            // 准备给客户端浏览器发送的数据
            // Unpooled.copiedBuffer(CharSequence string, Charset charset) --> 创建一个新的缓冲区，其内容 string在指定的charset字符集
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello Client", CharsetUtil.UTF_8);

            // 设置 HTTP 版本和 HTTP 的状态码, 返回内容
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            // 设置 HTTP 请求头，设置内容类型是文本类型
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");

            // 设置返回内容的长度
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            // 写出 HTTP 数据
            ctx.writeAndFlush(defaultFullHttpResponse);
        }
    }
}