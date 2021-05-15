package com.gateway.request;

import com.common.result.ResultGenerator;
import com.common.utils.JsonUtil;
import com.gateway.response.Flush;
import com.gateway.router.Router;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class RequestWorker implements Runnable {
    private final ChannelHandlerContext ctx;
    private final Router router;
    private final Map<String, String> params;
    private String route;

    public RequestWorker(ChannelHandlerContext ctx, Router router, Map<String, String> params) {
        this.ctx = ctx;
        this.router = router;
        this.params = params;
    }

    @Override
    public void run() {
        // Xss 攻击
        // sql 注入
        try {
            log.info(SessionHolder.getsession(ctx.channel()) + "请求" + JsonUtil.toJsonStr(params));
            String result = router.handle(params);
            Flush.flush(ctx, result, false);
        } catch (Exception e) {
            log.error("请求逻辑中出错{}", e);
            Flush.flush(ctx, JsonUtil.toJsonStr(ResultGenerator.genFailResult(null)), true);
        }


    }

}
