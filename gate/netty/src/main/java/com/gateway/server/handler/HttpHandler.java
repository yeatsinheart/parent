package com.gateway.server.handler;

import com.common.constant.Language;
import com.common.result.ResultGenerator;
import com.gateway.server.RequestHandler;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@ChannelHandler.Sharable
@Component("http")
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Autowired
    RequestHandler requestHandler;
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {
        String response = requestHandler.dispatch(ctx,httpRequest);
        if(StringUtils.isNotEmpty(response)){
            Flush.flushHttp(ctx,response);
        }else{
            Flush.flushHttp(ctx, new Gson().toJson(ResultGenerator.genFailResult(Language.中文.getCode())));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        if (e instanceof ReadTimeoutException) {
        } else {
            log.error("{}", e);
        }
        if (ctx.channel().isActive()) {
            ctx.close();
        }
    }
}
