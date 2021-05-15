package com.gateway.request;

import com.common.pool.ThreadPool;
import com.common.utils.JsonUtil;
import com.gateway.router.Router;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component("requestHandler")
public class RequestHandler {
    @Autowired
    Map<String, Router> routerMap;
    @Autowired
    RequestHandler requestHandler;
    @Autowired
    ThreadPool threadPool;

    private String doByteBuf(ByteBuf paramsByteBuf) {
        byte[] req = new byte[paramsByteBuf.readableBytes()];
        paramsByteBuf.readBytes(req);
        String reqStr = null;
        try {
            reqStr = new String(req, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            log.warn("Exception:", e);
        }
        return reqStr;
    }

    public void websocket(ChannelHandlerContext ctx, ByteBuf paramsByteBuf) {
        String reqStr = doByteBuf(paramsByteBuf);
        if (StringUtils.isEmpty(reqStr)) {
            log.info("websocket收到空信息");
            return;
        }
        String router = SessionHolder.getUri(ctx.channel());
        if (StringUtils.isEmpty(router)) {
            return;
        }
        Map<String, String> map;
        try {
            map = JsonUtil.toMap(reqStr, String.class);
        } catch (Exception e) {
            log.warn("websocket请求不是json");
            return;
        }
        requestHandler.dispatch(ctx, router, map);
    }

    // 统一请求处理入口 避免netty工作线程占用，额外开启线程。
    public void dispatch(ChannelHandlerContext ctx, String route, Map<String, String> map) {
        Router router = routerMap.getOrDefault("gateway_route_" + route, routerMap.get("gateway_route_default"));
        threadPool.limitedNumExecutor.execute(new RequestWorker(ctx, router, map));
    }
}
