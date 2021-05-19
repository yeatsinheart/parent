package com.gateway.server.handler;

import com.gateway.request.GateRequest;
import com.gateway.request.RequestParamUtil;
import com.gateway.server.decode.WebSocketRequestDTO;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.ReferenceCounted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@ChannelHandler.Sharable
@Service
public class HttpDispatcher extends AbstractRequestHandler<ReferenceCounted> {


    @Autowired
    private HttpHandler http;
    @Autowired
    private WebSocketHandler webSocket;

    @Override
    protected GateRequest getRouterRequest(ChannelHandlerContext ctx, ReferenceCounted msg) {
        // 分发请求，自动引用计数，到具体Handler前需要手动retain,否则会多减一次,直接报错
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            handShake(ctx, request);
            fileUpload(ctx, request);
            http(ctx, request);
        } else if (msg instanceof WebSocketFrame) {
            websocket(ctx, (WebSocketFrame) msg);
        } else {
            log.error("什么鬼消息" + msg);
        }
        // 分发请求，不需要返回具体的路由请求
        return null;
    }

    private void handShake(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (RequestParamUtil.isWebSocketHandShake(request)) {
            //websocket  握手处理
            WebSocketRequestDTO webSocketRequest = new WebSocketRequestDTO(request.retain());
            ctx.pipeline().addLast(webSocket);
            ctx.fireChannelRead(webSocketRequest);
        }
    }

    private void websocket(ChannelHandlerContext ctx, WebSocketFrame request) {
        // websocket 消息
        WebSocketRequestDTO webSocketRequest = new WebSocketRequestDTO(request.retain());
        ctx.pipeline().addLast(webSocket);
        ctx.fireChannelRead(webSocketRequest);
    }

    private void fileUpload(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (RequestParamUtil.isFileUpload(request)) {
            //文件上传处理器,网关没必要文件上传
            ctx.fireChannelRead(new FileUploadRequestVo(request.retain()));
        }
    }

    private void http(ChannelHandlerContext ctx, FullHttpRequest request) {
        ctx.pipeline().addLast(http);
        ctx.fireChannelRead(request.retain());
    }


    @NoArgsConstructor
    @Data
    @EqualsAndHashCode(callSuper = false)
    public class FileUploadRequestVo {
        private FullHttpRequest request;

        public FileUploadRequestVo(FullHttpRequest request) {
            this.request = request;
        }
    }
}
