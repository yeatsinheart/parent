package com.gateway.project.impl;

import com.base.result.ResultGenerator;
import com.base.utils.JsonUtil;
import com.base.utils.NamingThreadFactory;
import com.base.utils.StringUtil;
import com.gateway.auth.Auth;
import com.gateway.dubbo.DubboInvoke;
import com.gateway.dubbo.DubboRequest;
import com.gateway.dubbo.caller.RemoteApi;
import com.gateway.dubbo.meta.MetadataCollector;
import com.gateway.project.GateRequest;
import com.gateway.project.Router;
import com.gateway.response.Flush;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component(Router.ROUTER_KEY + "default")
public class DefaultRouter implements Router {
    // io请求线程池
    protected ExecutorService ioworker;
    @Value("${netty.call.num:1}")
    private int callNum;
    @Autowired
    private DubboInvoke dubboInvoke;
    @Resource
    private MetadataCollector metadataCollector;

    @PostConstruct
    public void init() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(callNum, callNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new NamingThreadFactory("DefaultRouter.java"));
        executor.prestartAllCoreThreads();
        ioworker = executor;
    }

    /**
     * 根据api查找具体的接口定义
     */
    public RemoteApi getApiInfo(GateRequest gateRequest) {
        // todo 从header中获取有问题，如果tcp udp 是没有header的
        String language = gateRequest.getHeaders().get("language");
        String currency = gateRequest.getHeaders().get("currency");
        String group = gateRequest.getHeaders().get("group");
        String version = gateRequest.getHeaders().get("version");
        Integer api = gateRequest.getHeaders().getInt("api");


        RemoteApi service = new RemoteApi();
        service.setGroup(StringUtil.isEmpty(group) ? "dev" : group);
        service.setVersion(StringUtil.isEmpty(version) ? "1.0.0" : version);
        return service;
    }

    /**
     * 填充基本请求信息
     */
    public Map<String, Object> initParam(GateRequest gateRequest) {
        Map<String, Object> param = gateRequest.getParams();
        return param;
    }

    /**
     * 获取调用功能信息
     */

    @Override
    public void handle(GateRequest gateRequest) {
        RemoteApi remoteApi = getApiInfo(gateRequest);
        if (null == remoteApi) {
            Flush.flush(gateRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), true);
            return;
        }
        // 如果带有加解密，就要求所有协议只能传String进来，然后各个路由中自己去维护加解密情况
        // 加解密，维护，可用状态，鉴权
        if (!Auth.auth(remoteApi, gateRequest)) {
            Flush.flush(gateRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), true);
            return;
        }
        // 是否多线程处理，考虑具体接口的io情况，延时高的，开启多线程
        if (1 == remoteApi.getMulti()) {
            ioworker.execute(() -> {
                invoke(remoteApi, gateRequest);
            });
        } else {
            invoke(remoteApi, gateRequest);
        }
    }

    private void invoke(RemoteApi remoteApi, GateRequest gateRequest) {
        Object result1 = dubboInvoke.invoke(remoteApi, initRemoteRequest(gateRequest));
        Flush.flush(gateRequest, JsonUtil.toJsonStr(result1), false);
    }

    private DubboRequest initRemoteRequest(GateRequest gateRequest) {
        DubboRequest dubboRequest = new DubboRequest();
        dubboRequest.setLanguage(gateRequest.getLanguage());
        dubboRequest.setCurrency(gateRequest.getCurrency());
        // 具体参数实现,约定只能使用BaseRequestDTO的实现类。
        Object[] dubboParam = new Object[]{initParam(gateRequest)};
        dubboRequest.setData(dubboParam);
        return dubboRequest;
    }
}
