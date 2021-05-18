package com.gateway.router.impl;

import com.base.result.ResultGenerator;
import com.base.utils.JsonUtil;
import com.base.utils.NamingThreadFactory;
import com.base.utils.StringUtil;
import com.gateway.auth.Auth;
import com.gateway.dubbo.DubboInvoke;
import com.gateway.dubbo.DubboRequest;
import com.gateway.dubbo.caller.RemoteApi;
import com.gateway.dubbo.meta.MetadataCollector;
import com.gateway.response.Flush;
import com.gateway.router.Router;
import com.gateway.router.RouterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component(Router.ROUTER_KEY + "default")
public class DefaultRouter implements Router {
    // io请求线程池
    protected ExecutorService ioworker = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors(),
            new NamingThreadFactory("req"));
    @Autowired
    private DubboInvoke dubboInvoke;
    @Resource
    private MetadataCollector metadataCollector;

    {

    }

    /**
     * 根据api查找具体的接口定义
     */
    public RemoteApi getApiInfo(RouterRequest routerRequest) {
        String language = routerRequest.getHeaders().get("language");

        String currency = routerRequest.getHeaders().get("currency");
        String group = routerRequest.getHeaders().get("group");
        String version = routerRequest.getHeaders().get("version");

        Integer api = (Integer) routerRequest.getHeaders().get("api");

        RemoteApi service = new RemoteApi();
        service.setGroup(StringUtil.isEmpty(group) ? "dev" : group);
        service.setVersion(StringUtil.isEmpty(version) ? "1.0.0" : version);
        return service;
    }

    /**
     * 填充基本请求信息
     */
    public Map<String, Object> initParam(RouterRequest routerRequest) {
        Map<String, Object> param = routerRequest.getParams();
        return param;
    }

    /**
     * 获取调用功能信息
     */

    @Override
    public void handle(RouterRequest routerRequest) {
        RemoteApi remoteApi = getApiInfo(routerRequest);
        if (null == remoteApi) {
            Flush.flush(routerRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), true);
            return;
        }
        // 如果带有加解密，就要求所有协议只能传String进来，然后各个路由中自己去维护加解密情况
        // 加解密，维护，可用状态，鉴权
        if (!Auth.auth(remoteApi, routerRequest)) {
            Flush.flush(routerRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), true);
            return;
        }
        // 是否多线程处理，考虑具体接口的io情况，延时高的，开启多线程
        if (1 == remoteApi.getMulti()) {
            ioworker.execute(() -> {
                invoke(remoteApi, routerRequest);
            });
        } else {
            invoke(remoteApi, routerRequest);
        }
    }

    private void invoke(RemoteApi remoteApi, RouterRequest routerRequest) {
        Object result1 = dubboInvoke.invoke(remoteApi, initRemoteRequest(routerRequest));
        Flush.flush(routerRequest, JsonUtil.toJsonStr(result1), false);
    }

    private DubboRequest initRemoteRequest(RouterRequest routerRequest) {
        DubboRequest dubboRequest = new DubboRequest();
        dubboRequest.setLanguage(routerRequest.getLanguage());
        dubboRequest.setCurrency(routerRequest.getCurrency());
        // 具体参数实现,约定只能使用BaseRequestDTO的实现类。
        Object[] dubboParam = new Object[]{initParam(routerRequest)};
        dubboRequest.setData(dubboParam);
        return dubboRequest;
    }
}
