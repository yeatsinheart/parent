package com.gateway.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class Flush {
    public static void setCros(FullHttpResponse response) {
        //允许跨域访问
        response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept,client,content-type");
        response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "GET,POST");
    }

    public static void badHttpRequest(ChannelHandlerContext ctx, Object result) {
        DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
        setCros(res);
        ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
        res.content().writeBytes(buf);
        buf.release();
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        f.addListener(ChannelFutureListener.CLOSE);
    }

    public static void flushHttp(ChannelHandlerContext ctx, String result) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        setCros(response);
        log.info("HTTP响应{}", result);
        ctx.writeAndFlush(response);
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public static void flushFinishWebSocket(Channel channel, Object result) {
        channel.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
    }

    public static void flushWebSocket(Channel channel, Object result) {
        channel.writeAndFlush(result);
    }
    public static void flushBytes(Channel channel, byte[] result) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(result);
        channel.writeAndFlush(buf);
    }
}
