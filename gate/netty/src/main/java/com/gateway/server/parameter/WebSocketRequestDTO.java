package com.gateway.server.parameter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class WebSocketRequestDTO {
    //握手请求
    private FullHttpRequest request;
    //websocket请求
    private WebSocketFrame frame;

    public WebSocketRequestDTO(FullHttpRequest request) {
        this.request = request;
    }

    public WebSocketRequestDTO(WebSocketFrame frame) {
        this.frame = frame;
    }

    public boolean release() {
        if (null != request) {
            if (request.refCnt() != 1) {
                log.error("http,引用计数不正常{}", request.refCnt());
                return false;
            } else {
                // return request.release();
            }
        }
        if (null != frame) {
            if (frame.refCnt() != 1) {
                log.error("websocket,引用计数不正常{}", request.refCnt());
                return false;
            } else {
                // return frame.release();
            }
        }
        return false;
    }

}
