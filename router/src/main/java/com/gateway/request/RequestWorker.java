package com.gateway.request;

import com.gateway.router.Router;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class RequestWorker implements Runnable {
    private final ChannelHandlerContext ctx;
    private String route;
    private final Router router;
    private final Map<String, String> params;

    public RequestWorker(ChannelHandlerContext ctx,Router router, Map<String, String> params) {
        this.ctx = ctx;
        this.router=router;
        this.params = params;
    }

    @Override
    public void run() {
        log.info("收到消息" + params);
        // Xss 攻击
        // sql 注入
        try {
            String result = router.handle(params);
        }catch (Exception e){
            log.error("请求逻辑中出错{}", e);
        }

    }

}
