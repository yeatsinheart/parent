package com.gateway.request;

import com.base.result.ResultGenerator;
import com.base.utils.JsonUtil;
import com.gateway.response.Flush;
import com.gateway.router.Router;
import com.gateway.router.RouterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("requestHandler")
public class RequestHandler {

    @Autowired
    Map<String, Router> routerMap;
    @Autowired
    RequestHandler requestHandler;

    // 统一请求处理入口 避免netty工作线程占用，额外开启线程。
    public void dispatch(RouterRequest routerRequest) {
        Router router = getRouter(routerRequest.getUri());
        if (null == router) {
            Flush.flush(routerRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), false);
            return;
        }
        // Xss 攻击  &&  sql 注入
        try {
            // 可能会异步处理请求咯
            router.handle(routerRequest);
        } catch (Exception e) {
            log.error("请求逻辑中出错{}", e);
            Flush.flush(routerRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), false);
        }
    }

    public Router getRouter(String uri) {
        if ("favicon.ico".equals(uri)) {
            return null;
        }
        String route = uri;
        //限制URI，只有符合网关可接收的uri才能进入请求，否则直接返回
        return routerMap.getOrDefault(Router.ROUTER_KEY + route, routerMap.get(Router.ROUTER_KEY + "default"));
    }
}
