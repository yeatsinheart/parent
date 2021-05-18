package com.gateway.server.parameter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.ReferenceCounted;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class WebSocketRequestDTO implements ReferenceCounted {
    //握手请求
    private FullHttpRequest handleShake;
    //websocket请求
    private WebSocketFrame frame;

    public WebSocketRequestDTO(FullHttpRequest request) {
        this.handleShake = request;
    }

    public WebSocketRequestDTO(WebSocketFrame frame) {
        this.frame = frame;
    }

    @Override
    public int refCnt() {
        if (null != handleShake) {
            return handleShake.refCnt();
        }
        if (null != frame) {
            return frame.refCnt();
        }
        return 0;
    }

    @Override
    public ReferenceCounted retain() {
        if (null != handleShake) {
            return handleShake.retain();
        }
        if (null != frame) {
            return frame.retain();
        }
        return null;
    }

    @Override
    public ReferenceCounted retain(int i) {
        if (null != handleShake) {
            return handleShake.retain(i);
        }
        if (null != frame) {
            return frame.retain(i);
        }
        return null;
    }

    @Override
    public ReferenceCounted touch() {
        if (null != handleShake) {
            return handleShake.touch();
        }
        if (null != frame) {
            return frame.touch();
        }
        return null;
    }

    @Override
    public ReferenceCounted touch(Object o) {
        if (null != handleShake) {
            return handleShake.touch(o);
        }
        if (null != frame) {
            return frame.touch(o);
        }
        return null;
    }

    public boolean release() {
        if (null != handleShake) {
            return handleShake.release();
        }
        if (null != frame) {
            return frame.release();
        }
        return false;
    }

    @Override
    public boolean release(int i) {
        if (null != handleShake) {
            return handleShake.release(i);
        }
        if (null != frame) {
            return frame.release(i);
        }
        return false;
    }

}
