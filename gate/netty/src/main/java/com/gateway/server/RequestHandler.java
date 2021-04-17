package com.gateway.server;

import com.common.dto.BaseRequest;
import com.common.utils.XssUtil;
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
import java.util.Map;

@Slf4j
@Component("requestHandler")
public class RequestHandler {
    @Autowired
    Map<String, Router> routerMap;

    // 统一处理tcp udp 的ByteBuf请求信息
    public String dispatch(ChannelHandlerContext ctx, ByteBuf paramsByteBuf) {
        byte[] req = new byte[paramsByteBuf.readableBytes()];
        paramsByteBuf.readBytes(req);
        String reqStr = null;
        try {
            reqStr = new String(req, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.warn("UnsupportedEncodingException:{}", e);
        }
        if (StringUtils.isEmpty(reqStr)) {
            log.info("收到空信息");
            return null;
        }
        reqStr=reqStr.replaceAll("\r","").replaceAll("\n","");
        log.info("ByteBuf请求{}", reqStr);
        String router = "udp/tcp_route";
        if (StringUtils.isEmpty(router)) {
            return null;
        }
        log.info(router);
        try {
            return dispatch(ctx, router, reqStr);
        } catch (Exception e) {
            log.error("ByteBuf请求逻辑中出错{}", e);
        }
        return null;
    }

    // 统一处理 http 的FullHttpRequest请求信息
    public String dispatch(ChannelHandlerContext ctx, FullHttpRequest paramsHttp) {
        String uri = ParamUtil.getUri(paramsHttp);
        log.info("访问uri{}",uri);
        if (uri.equals("/favicon.ico")) {
            return null;
        }
        Map<String, String> map = ParamUtil.getRequestParams(paramsHttp);
        try {
            return dispatch(ctx, uri, new Gson().toJson(map));
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
        // Xss 攻击
        // sql 注入
        BaseRequest request;
        try {
            request = (new Gson()).fromJson(paramsString, BaseRequest.class);
        } catch (Exception e) {
            log.info("未知类型:{}", paramsString);
            return null;
        }
        if (null == request) {
            log.warn("未知数据类型的用户消息");
            return null;
        }
        log.info("收到消息" + paramsString);
        return todo(ctx, route, request);
    }

    public String todo(ChannelHandlerContext ctx, String route, BaseRequest request) {
        log.info(new Gson().toJson(routerMap));
        Router router = routerMap.getOrDefault("route_" + route, routerMap.get("router_default"));
        try {
            return router.handle(request);
        } catch (Exception e) {
            log.error("请求逻辑中出错{}", e);
        }
        return null;
    }
}
