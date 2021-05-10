package com.gateway.server.handler;

import com.common.constant.Language;
import com.common.result.ResultGenerator;
import com.common.utils.JsonUtil;
import com.gateway.server.SessionHolder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@ChannelHandler.Sharable
@Component("http")
public class HttpHandler extends AbstractRequestHandler<FullHttpRequest> {
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SessionHolder.setProto(ctx.channel(), "http");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) {
        SessionHolder.setIP(ctx.channel());
        String response = requestHandler.http(ctx, httpRequest);
        if (StringUtils.isNotEmpty(response)) {
            Flush.flushHttp(ctx, response);
        } else {
            Flush.flushHttp(ctx, JsonUtil.toJsonStr(ResultGenerator.genFailResult(Language.中文.getCode())));
        }
    }
}
