package com.gateway.server.handler;

import com.common.constant.Language;
import com.common.result.ResultGenerator;
import com.gateway.server.RequestHandler;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@ChannelHandler.Sharable
@Component("udp")
public class UDPHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Autowired
    RequestHandler requestHandler;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        if (e instanceof ReadTimeoutException) {
        } else {
            log.error("{}", e);
        }
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf bytebuf) throws Exception {
        String response = requestHandler.dispatch(ctx, bytebuf);
        if (StringUtils.isNotEmpty(response)) {
            Flush.flushBytes(ctx.channel(), response.getBytes());
        } else {
            Flush.flushBytes(ctx.channel(), new Gson().toJson(ResultGenerator.genFailResult(Language.中文.getCode())).getBytes());
        }
    }
}