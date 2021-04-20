package com.gateway.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ChannelHandler.Sharable
@Component("udp")
public class UDPHandler extends AbstractRequestHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf bytebuf)  {
        doBytebuf(ctx, bytebuf);
    }
}