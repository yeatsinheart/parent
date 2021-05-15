package com.gateway.request;

import com.common.pool.ThreadPool;
import com.common.utils.JsonUtil;
import com.gateway.router.Router;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
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
        String router = ctx.channel().attr(SessionHolder.URI).get();
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

    // 统一处理tcp udp 的ByteBuf请求信息  tcp udp websocket 内容应该都一致。。
    public void byteBuf(ChannelHandlerContext ctx, ByteBuf paramsByteBuf) {
        String reqStr = doByteBuf(paramsByteBuf);
        if (StringUtils.isEmpty(reqStr)) {
            log.info("ByteBuf请求收到空信息");
            return;
        }
        //reqStr = reqStr.replaceAll("\r", "").replaceAll("\n", "");
        log.info("ByteBuf请求{}", reqStr);
        // todo 请求的路由处理
        Map<String, String> map;
        try {
            map = JsonUtil.toMap(reqStr, String.class);
        } catch (Exception e) {
            log.warn("ByteBuf请求不是json");
            return;
        }
        String router = "/";
        requestHandler.dispatch(ctx, router, map);

    }

    // 统一处理 http 的FullHttpRequest请求信息
    public String http(ChannelHandlerContext ctx, FullHttpRequest paramsHttp) {
        String uri = ParamUtil.getUri(paramsHttp);
        if (uri.equals("/favicon.ico")) {
            return null;
        }
        Map<String, String> map = ParamUtil.getRequestParams(paramsHttp);
        try {
            requestHandler.dispatch(ctx, uri, map);
        } catch (Exception e) {
            log.error("Http请求逻辑中出错", e);
        }
        return null;
    }

    // 统一请求处理入口 避免netty工作线程占用，额外开启线程。
    public void dispatch(ChannelHandlerContext ctx, String route, Map<String, String> map) {
        Router router = routerMap.getOrDefault("gateway_route_" + route, routerMap.get("gateway_route_default"));
        threadPool.unLimitedExecutor.execute(new RequestWorker(ctx, router, map));

    }
}
