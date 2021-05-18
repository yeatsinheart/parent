package com.gateway.server.handler;

import com.gateway.request.RequestParamUtil;
import com.gateway.router.RouterRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@ChannelHandler.Sharable
@Component("tcp")
public class TcpHandler extends AbstractRequestHandler<ByteBuf> {
    protected String protocol = "tcp";

    @Override
    protected RouterRequest getRouterRequest(ChannelHandlerContext ctx, ByteBuf reuqest) {
        RouterRequest routerRequest = new RouterRequest();
        routerRequest.setCtx(ctx);
        Map<String, Object> params = RequestParamUtil.getRequestParams(reuqest);
        routerRequest.setParams(params);
        routerRequest.setUri((String) params.get("uri"));
        routerRequest.setLanguage((String) params.get("language"));
        return routerRequest;

    }
}