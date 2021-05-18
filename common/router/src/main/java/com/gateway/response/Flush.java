package com.gateway.response;

import com.gateway.request.SessionHolder;
import com.gateway.router.RouterRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class Flush {

    public static void setCros(FullHttpResponse response) {
        //允许跨域访问
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept,client,content-type");
        response.headers().set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST");

        //设置cookie
        //设置Secure;HttpOnly
        //response.headers().set(SET_COOKIE, "JSESSIONID=" + sessionid + ";Secure;HttpOnly")
        //response.headers().set("x-frame-options", "SAMEORIGIN");//设置x-frame-options
    }

    public static void badHttpRequest(ChannelHandlerContext ctx, Object result) {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
        if (SessionHolder.getKeeplive(ctx.channel())) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            // Add keep alive header as per:
            // - http://www.w3.org/Protocols/HTTP/1.1/draft-ietf-http-v11-spec-01.html#Connection
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        setCros(response);
        ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
        response.content().writeBytes(buf);
        buf.release();
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        f.addListener(ChannelFutureListener.CLOSE);
    }

    public static void flushFinishWebSocket(Channel channel, Object result) {
        channel.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
    }

    public static void flush(RouterRequest routerRequest, String result, boolean closeNow) {
        String proto = SessionHolder.getProto(routerRequest.getCtx().channel());
        ChannelFuture future;
        switch (proto) {
            case "http":
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));
                setCros(response);
                if (SessionHolder.getKeeplive(routerRequest.getCtx().channel())) {
                    response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
                    response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                }
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
                future = routerRequest.getCtx().writeAndFlush(response);
                break;
            default:
                ByteBuf buf = Unpooled.buffer();
                buf.writeBytes(result.getBytes(StandardCharsets.UTF_8));
                future = routerRequest.getCtx().channel().writeAndFlush(buf);
                break;
        }
        if (closeNow) {
            //如果直接关闭通道并且内部队列中仍有数据，则会出现异常。
            future.addListener(ChannelFutureListener.CLOSE);
        }
        log.info("{}耗时{}响应{}，是否[{}]中断请求", System.currentTimeMillis()-routerRequest.getCreateTime(),SessionHolder.getsession(routerRequest.getCtx().channel()), result, closeNow);
    }

}
