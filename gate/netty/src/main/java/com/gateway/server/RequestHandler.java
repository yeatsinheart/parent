package com.gateway.server;

import com.gateway.router.Router;
import com.gateway.server.parameter.ParamUtil;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("requestHandler")
public class RequestHandler {
    @Autowired
    Map<String, Router> routerMap;
    @Autowired
    RequestHandler requestHandler;

    private String doByteBuf(ByteBuf paramsByteBuf) {
        byte[] req = new byte[paramsByteBuf.readableBytes()];
        paramsByteBuf.readBytes(req);
        String reqStr = null;
        try {
            reqStr = new String(req, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.warn("UnsupportedEncodingException:{}", e);
        }
        return reqStr;
    }

    public String websocket(ChannelHandlerContext ctx, ByteBuf paramsByteBuf) {
        String reqStr = doByteBuf(paramsByteBuf);
        if (StringUtils.isEmpty(reqStr)) {
            log.info("websocket收到空信息");
            return null;
        }
        String router = ctx.channel().attr(SessionHolder.URI).get();
        if (StringUtils.isEmpty(router)) {
            return null;
        }
        try {
            return requestHandler.dispatch(ctx, router, reqStr);
        } catch (Exception e) {
            log.error("websocket请求逻辑中出错{}", e);
        }
        return null;
    }

    // 统一处理tcp udp 的ByteBuf请求信息  tcp udp websocket 内容应该都一致。。
    public String byteBuf(ChannelHandlerContext ctx, ByteBuf paramsByteBuf) {
        String reqStr = doByteBuf(paramsByteBuf);
        if (StringUtils.isEmpty(reqStr)) {
            log.info("ByteBuf请求收到空信息");
            return null;
        }
        //reqStr = reqStr.replaceAll("\r", "").replaceAll("\n", "");
        log.info("ByteBuf请求{}", reqStr);
        // todo 请求的路由处理


        HashMap<String, String> request = new HashMap<>();
        try {
            request = new Gson().fromJson(reqStr, HashMap.class);
        } catch (Exception e) {
            log.warn("ByteBuf请求不是json");
            return null;
        }
        String router = "/";
        if (StringUtils.isEmpty(router)) {
            return null;
        }
        try {
            return requestHandler.dispatch(ctx, router, reqStr);
        } catch (Exception e) {
            log.error("ByteBuf请求逻辑中出错{}", e);
        }
        return null;
    }

    // 统一处理 http 的FullHttpRequest请求信息
    public String http(ChannelHandlerContext ctx, FullHttpRequest paramsHttp) {
        String uri = ParamUtil.getUri(paramsHttp);
        if (uri.equals("/favicon.ico")) {
            return null;
        }
        Map<String, String> map = ParamUtil.getRequestParams(paramsHttp);
        try {
            return requestHandler.dispatch(ctx, uri, new Gson().toJson(map));
        } catch (Exception e) {
            log.error("Http请求逻辑中出错{}", e);
        }
        return null;
    }

    // 统一请求处理入口
    public String dispatch(ChannelHandlerContext ctx, String route, String paramsString) {
        if (StringUtils.isEmpty(paramsString)) {
            log.info("收到空信息,直接结束对话，还是保持session？");
            return null;
        }
        log.info("收到消息" + paramsString);
        // Xss 攻击
        // sql 注入
        Router router = routerMap.getOrDefault("gateway_route_" + route, routerMap.get("gateway_route_default"));
        try {
            return router.handle(paramsString);
        } catch (Exception e) {
            log.error("请求逻辑中出错{}", e);
        }
        return null;
    }
}
