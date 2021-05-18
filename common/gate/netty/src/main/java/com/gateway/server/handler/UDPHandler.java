package com.gateway.server.handler;

import com.gateway.request.SessionHolder;
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
    protected void doRequest(ChannelHandlerContext ctx, ByteBuf reuqest) {
        SessionHolder.setProto(ctx.channel(), "udp");
        doBytebuf(ctx, reuqest);
    }
}