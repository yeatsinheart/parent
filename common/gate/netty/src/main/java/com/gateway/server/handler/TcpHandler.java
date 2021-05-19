package com.gateway.server.handler;

import com.gateway.request.GateRequest;
import com.gateway.request.RequestParamUtil;
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
    String protocol = "tcp";

    @Override
    protected GateRequest getRouterRequest(ChannelHandlerContext ctx, ByteBuf reuqest) {
        GateRequest gateRequest = new GateRequest();
        gateRequest.setProtocal(protocol);
        gateRequest.setCtx(ctx);
        Map<String, Object> params = RequestParamUtil.getRequestParams(reuqest);
        gateRequest.setParams(params);
        gateRequest.setUri((String) params.get("uri"));
        gateRequest.setLanguage((String) params.get("language"));
        return gateRequest;

    }
}