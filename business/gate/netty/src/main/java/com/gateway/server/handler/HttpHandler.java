package com.gateway.server.handler;

import com.common.pool.ThreadPool;
import com.common.result.ResultGenerator;
import com.common.utils.JsonUtil;
import com.gateway.request.ParamUtil;
import com.gateway.request.SessionHolder;
import com.gateway.response.Flush;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@ChannelHandler.Sharable
@Component("http")
public class HttpHandler extends AbstractRequestHandler<FullHttpRequest> {
    @Autowired
    ThreadPool threadPool;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SessionHolder.setIP(ctx.channel());
        SessionHolder.setProto(ctx.channel(), "http");
        super.channelActive(ctx);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {
        SessionHolder.setProto(ctx.channel(), "http");
        SessionHolder.setIP(ctx.channel());
        SessionHolder.setKeeplive(ctx.channel(),
                !httpRequest.headers()
                        .containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE, true)
                        && (
                        httpRequest.protocolVersion().isKeepAliveDefault()
                                || httpRequest.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE, true)));
        String uri = ParamUtil.getUri(httpRequest);
        if (uri.equals("/favicon.ico")) {
            Flush.flush(ctx,
                    JsonUtil.toJsonStr(ResultGenerator.genFailResult(null)),
                    true);
            return;
        }
        Map<String, String> map = ParamUtil.getRequestParams(httpRequest);
        requestHandler.dispatch(ctx, uri, map);
    }
}
