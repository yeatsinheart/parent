package com.gateway.server.handler;

import com.common.constant.Language;
import com.common.result.ResultGenerator;
import com.gateway.server.RequestHandler;
import com.gateway.server.SessionHolder;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
        lossConnectCount = 0;
        int nowRef = bytebuf.refCnt();
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

    /**
     * 一段时间未进行读写操作 回调
     * 服务端在10s内未进行读写操作，就会向客户端发送心跳包，
     * 客户端收到心跳包后立即回复心跳包给服务端，此时服务端就进行了读操作，也就不会触发IdleState.READER_IDLE（未读操作状态），
     * 若客户端异常掉线了，并不能响应服务端发来的心跳包，在25s后就会触发IdleState.READER_IDLE（未读操作状态），此时服务器就会将通道关闭
     */
    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE) && ++lossConnectCount > 2) {
                //未进行读操作
                System.out.println("READER_IDLE");
                // 超时关闭channel
                ctx.close();
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {

            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                //未进行读写
                System.out.println("ALL_IDLE");
                // 发送心跳消息
                //MsgHandleService.getInstance().sendMsgUtil.sendHeartMessage(ctx); } }
            }
        }
    }
}
