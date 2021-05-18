package com.gateway.server.handler;

import com.gateway.request.RequestParamUtil;
import com.gateway.request.SessionHolder;
import com.gateway.router.RouterRequest;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ChannelHandler.Sharable
@Component("http")
public class HttpHandler extends AbstractRequestHandler<FullHttpRequest> {
    @Override
    protected void doRequest(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {
        SessionHolder.setProto(ctx.channel(), "http");
        String uri = RequestParamUtil.getUri(httpRequest);
        SessionHolder.setKeeplive(ctx.channel(),
                !httpRequest.headers()
                        .containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE, true)
                        && (
                        httpRequest.protocolVersion().isKeepAliveDefault()
                                || httpRequest.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE, true)));

        Map<String, Object> params = RequestParamUtil.getRequestParams(httpRequest);
        RouterRequest routerRequest = new RouterRequest();
        routerRequest.setCtx(ctx);
        routerRequest.setParams(params);
        routerRequest.setUri(uri.substring(1));
        routerRequest.setHeaders(new HashMap<>());
        requestHandler.dispatch(routerRequest);

    }
}
