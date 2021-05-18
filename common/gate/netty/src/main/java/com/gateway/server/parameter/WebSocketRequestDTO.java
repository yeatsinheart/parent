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
    private FullHttpRequest woshou;
    //websocket请求
    private WebSocketFrame frame;

    public WebSocketRequestDTO(FullHttpRequest request) {
        this.woshou = request;
    }

    public WebSocketRequestDTO(WebSocketFrame frame) {
        this.frame = frame;
    }

    @Override
    public int refCnt() {
        if(null!=woshou){return  woshou.refCnt();}
        if(null!=frame){return  frame.refCnt();}
        return 0;
    }

    @Override
    public ReferenceCounted retain() {
        if(null!=woshou){return  woshou.retain();}
        if(null!=frame){return  frame.retain();}
        return null;
    }

    @Override
    public ReferenceCounted retain(int i) {
        if(null!=woshou){return  woshou.retain(i);}
        if(null!=frame){return  frame.retain(i);}
        return null;
    }

    @Override
    public ReferenceCounted touch() {
        if(null!=woshou){return  woshou.touch();}
        if(null!=frame){return  frame.touch();}
        return null;
    }

    @Override
    public ReferenceCounted touch(Object o) {
        if(null!=woshou){return  woshou.touch(o);}
        if(null!=frame){return  frame.touch(o);}
        return null;
    }

    public boolean release() {
        if(null!=woshou){return  woshou.release();}
        if(null!=frame){return  frame.release();}
        return false;
    }

    @Override
    public boolean release(int i) {
        if(null!=woshou){return  woshou.release(i);}
        if(null!=frame){return  frame.release(i);}
        return false;
    }

}
