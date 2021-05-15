package com.gateway.server.handler;

import com.gateway.request.SessionHolder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ChannelHandler.Sharable
@Component("tcp")
public class TCPHandler extends AbstractRequestHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SessionHolder.setIP(ctx.channel());
        SessionHolder.setProto(ctx.channel(), "tcp");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf bytebuf) {
        doBytebuf(ctx, bytebuf);
    }
}