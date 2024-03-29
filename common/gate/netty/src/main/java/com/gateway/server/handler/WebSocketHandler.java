package com.gateway.server.handler;

import com.gateway.request.GateRequest;
import com.gateway.request.RequestParamUtil;
import com.gateway.request.SessionHolder;
import com.gateway.response.Flush;
import com.gateway.server.decode.WebSocketRequestDTO;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@ChannelHandler.Sharable
@Component("websocket")
public class WebSocketHandler extends AbstractRequestHandler<WebSocketRequestDTO> {
    private final WebSocketServerHandshakerFactory wsFactory =
            new WebSocketServerHandshakerFactory(null, null, true, 65536 * 5);
    String protocol = "ws";

    @Override
    protected GateRequest getRouterRequest(ChannelHandlerContext ctx, WebSocketRequestDTO request) {
        SessionHolder.setProto(ctx.channel(), "ws");
        //处理握手
        if (request.getHandleShake() != null) {
            this.handleShake(ctx, request.getHandleShake());
            return null;
        }
        //处理websocket数据
        if (request.getFrame() != null) {
            return this.handleFrame(ctx, request.getFrame());
        }
        return null;
    }

    //处理握手
    private void handleShake(ChannelHandlerContext ctx, FullHttpRequest request) {
        // 根据host获取到对应的appId
        String host = request.headers().get(HttpHeaderNames.REFERER);
        Map<String, Object> params = RequestParamUtil.getRequestParams(request);
        String uri = RequestParamUtil.getUri(request);
        if (StringUtils.isEmpty("握手信息")) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            ctx.close();
            return;
        }
        WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            ctx.close();
        } else {
            handshaker.handshake(ctx.channel(), request);
            ctx.channel().attr(SessionHolder.HAND_SHAKE_ATTR).set(handshaker);
            ctx.channel().attr(SessionHolder.URI).set(uri);
            ctx.channel().attr(SessionHolder.USER).set((String) params.get("token"));
            //topicListener.addListenerInfo(ctx.channel(), user.getAppId(), user.getUserId(), user.getUserName());
        }
    }

    //处理websocket数据
    private GateRequest handleFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            WebSocketServerHandshaker handshaker = ctx.channel().attr(SessionHolder.HAND_SHAKE_ATTR).get();
            if (handshaker == null) {
                Flush.flushFinishWebSocket(ctx.channel(), Unpooled.EMPTY_BUFFER);
            }
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        } else if (frame instanceof PingWebSocketFrame) {
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content()));
        } else if (frame instanceof TextWebSocketFrame) {
            Channel channel = ctx.channel();
            //TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            //String request = textFrame.text();
            doBytebuf(ctx, frame.content());
            //.writeAndFlush(new TextWebSocketFrame(msg));
        } else if (frame instanceof BinaryWebSocketFrame) {
            /*ByteBuf binaryFrame = frame.content();
            binaryFrame.markReaderIndex();
            int flag = binaryFrame.readInt();
            int strLength = binaryFrame.readInt();
            // 文字信息
            String str = null;

            byte[] bytes = new byte[strLength];
            int index = binaryFrame.readerIndex();
            binaryFrame.getBytes(index, bytes, 0, strLength);
            str = new String(bytes, 0, strLength);

            if (XssUtil.stripXSS(str)) {
                TextWebSocketFrame responseFrame = new TextWebSocketFrame("{\"sender\":\"notify\",\"data\":\"消息内容异常，请核对后再发送\"}");
                Flush.flushWebSocket(ctx.channel(), responseFrame);
                return;
            }*/


        }
        return null;
    }

}