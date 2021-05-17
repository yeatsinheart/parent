package com.gateway.request;

import com.base.pool.ThreadPool;
import com.base.utils.NamingThreadFactory;
import com.gateway.router.Router;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component("requestHandler")
public class RequestHandler {
    @Autowired
    Map<String, Router> routerMap;
    @Autowired
    RequestHandler requestHandler;
    @Autowired
    ThreadPool threadPool;

    protected ExecutorService requestWorker = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new NamingThreadFactory("handle"));


    // 统一请求处理入口 避免netty工作线程占用，额外开启线程。
    public void dispatch(ChannelHandlerContext ctx, String route, Map<String, Object> map) {
        Router router = routerMap.getOrDefault("gateway_route_" + route, routerMap.get("gateway_route_default"));
        requestWorker.execute(new RequestWorker(ctx, router, map));
    }
}
