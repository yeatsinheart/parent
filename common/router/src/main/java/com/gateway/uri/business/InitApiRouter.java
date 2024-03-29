package com.gateway.uri.business;

import com.base.result.ResultGenerator;
import com.base.utils.JsonUtil;
import com.gateway.dubbo.caller.CallerCache;
import com.gateway.dubbo.meta.MetadataCollector;
import com.gateway.request.GateRequest;
import com.gateway.request.Router;
import com.gateway.response.Flush;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component(Router.ROUTER_KEY + "init")
public class InitApiRouter implements Router {

    @Resource
    private CallerCache callerCache;
    @Resource
    private MetadataCollector metadataCollector;

    @Override
    public void handle(GateRequest routerRequest) {
        callerCache.clear();
        metadataCollector.clear();
        Flush.flush(routerRequest, JsonUtil.toJsonStr(ResultGenerator.genSuccessResult(null)), true);
    }
}
