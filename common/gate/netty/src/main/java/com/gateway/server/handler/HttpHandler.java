package com.gateway.server.handler;

import com.gateway.project.GateRequest;
import com.gateway.request.RequestParamUtil;
import com.gateway.request.SessionHolder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@ChannelHandler.Sharable
@Component("http")
public class HttpHandler extends AbstractRequestHandler<FullHttpRequest> {
    String protocol = "http";

    @Override
    protected GateRequest getRouterRequest(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {

        String uri = RequestParamUtil.getUri(httpRequest);
        SessionHolder.setKeeplive(ctx.channel(),
                !httpRequest.headers()
                        .containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE, true)
                        && (
                        httpRequest.protocolVersion().isKeepAliveDefault()
                                || httpRequest.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE, true)));

        GateRequest gateRequest = new GateRequest();
        gateRequest.setProtocal(protocol);
        gateRequest.setCtx(ctx);
        gateRequest.setLanguage("dev");

        // http请求需要去除首位的/
        gateRequest.setUri(uri.substring(1));
        gateRequest.setHeaders(httpRequest.headers());

        // 如果带有加解密，就要求所有协议只能传String进来，然后各个路由中自己去维护加解密情况
        // GET 请求也有参数
        // POST 可以传加密信息，或者纯文本
        Map<String, Object> params = RequestParamUtil.getRequestParams(httpRequest);
        gateRequest.setParams(params);
        return gateRequest;


    }
}
