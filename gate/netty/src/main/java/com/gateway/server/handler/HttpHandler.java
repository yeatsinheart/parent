package com.gateway.server.handler;

import com.common.pool.ThreadPool;
import com.gateway.request.SessionHolder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@ChannelHandler.Sharable
@Component("http")
public class HttpHandler extends AbstractRequestHandler<FullHttpRequest> {
    @Autowired
    ThreadPool threadPool;
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SessionHolder.setProto(ctx.channel(), "http");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {
        SessionHolder.setProto(ctx.channel(), "http");
        SessionHolder.setIP(ctx.channel());
        requestHandler.http(ctx, httpRequest);
    }
}
