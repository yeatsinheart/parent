package com.gateway.server.handler;

import com.gateway.request.GateRequest;
import com.gateway.request.RequestHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCounted;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Slf4j
public abstract class AbstractRequestHandler<T extends ReferenceCounted> extends SimpleChannelInboundHandler<T> {
    @Autowired
    RequestHandler requestHandler;
    /**
     * 一段时间未进行读写操作 回调
     * 服务端在10s内未进行读写操作，就会向客户端发送心跳包，
     * 客户端收到心跳包后立即回复心跳包给服务端，此时服务端就进行了读操作，也就不会触发IdleState.READER_IDLE（未读操作状态），
     * 若客户端异常掉线了，并不能响应服务端发来的心跳包，在25s后就会触发IdleState.READER_IDLE（未读操作状态），此时服务器就会将通道关闭
     */
    private final int lossConnectCount = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T reuqest) {
        int nowRef = reuqest.refCnt();
        //收集请求信息分发
        GateRequest gateRequest = getRouterRequest(ctx, reuqest);
        if (null != gateRequest) {
            requestHandler.dispatch(gateRequest);
        }
        if (reuqest.refCnt() != nowRef) {
            log.error("http,引用计数不正常{}", reuqest.refCnt());
        }
    }

    protected abstract GateRequest getRouterRequest(ChannelHandlerContext ctx, T reuqest);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
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
    public void doBytebuf(ChannelHandlerContext ctx, ByteBuf bytebuf) {

    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE) ) {
                //未进行读操作 请求
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                //未写   回传
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
            }
            //log.info("懒得心跳了。直接挂掉" + SessionHolder.getsession(ctx.channel()));
            ctx.close();
            //Flush.flush(ctx, "{\"code\":886,\"message\":\"bye bye\"}", true);
        }
        super.userEventTriggered(ctx, evt);
    }
}
