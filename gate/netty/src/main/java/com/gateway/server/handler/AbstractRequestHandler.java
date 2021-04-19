package com.gateway.server.handler;

import com.common.constant.Language;
import com.common.result.ResultGenerator;
import com.gateway.server.RequestHandler;
import com.gateway.server.SessionHolder;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Slf4j
public abstract class AbstractRequestHandler<T> extends SimpleChannelInboundHandler<T> {
    @Autowired
    RequestHandler requestHandler;
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("👏👏👏👏👏👏👏👏👏👏👏👏👏👏👏");
        SessionHolder.setIP(ctx.channel());
        super.channelActive(ctx);
    }
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        if (e instanceof ReadTimeoutException) {
        } else {
            log.error("{}", e);
        }
        if (ctx.channel().isActive()) {
            ctx.close();
        }
    }

    public void doBytebuf(ChannelHandlerContext ctx, ByteBuf bytebuf) {
        int nowRef =  bytebuf.refCnt();
        String response = requestHandler.byteBuf(ctx, bytebuf);
        if (StringUtils.isNotEmpty(response)) {
            Flush.flushBytes(ctx.channel(), response.getBytes());
        } else {
            Flush.flushBytes(ctx.channel(), new Gson().toJson(ResultGenerator.genFailResult(Language.中文.getCode())).getBytes());
        }
        if (bytebuf.refCnt() != nowRef) {
            log.error("http,引用计数不正常{}", bytebuf.refCnt());
        }
    }
}
