package com.gateway.router.impl;

import com.base.i18n.I18nContext;
import com.base.result.ResultGenerator;
import com.base.utils.JsonUtil;
import com.base.utils.NamingThreadFactory;
import com.base.utils.StringUtil;
import com.gateway.auth.Auth;
import com.gateway.dubbo.*;
import com.gateway.dubbo.meta.MetadataCollector;
import com.gateway.dubbo.caller.DubboRemoteService;
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

    /**
     * 根据api查找具体的接口定义
     */
    public DubboRemoteService getApi(Integer api) {
        DubboRemoteService service = new DubboRemoteService();
        return service;
    }

    /**
     * 填充基本请求信息
     */
    public Map<String, Object> initParam(RouterRequest routerRequest) {
        Map<String, Object> param = routerRequest.getParams();
        return param;
    }

    @Override
    public void handle(RouterRequest routerRequest) {
        //{data:[{}]}
        //ioworker.execute(new RequestWorker(ctx, router, map));
        String language = (String) routerRequest.getHeaders().get("language");
        //初始化线程语言
        new I18nContext(language);
        String currency = (String) routerRequest.getHeaders().get("currency");
        String group = (String) routerRequest.getHeaders().get("group");
        String version = (String) routerRequest.getHeaders().get("version");

        Integer api = (Integer) routerRequest.getHeaders().get("api");

        DubboRemoteService service = getApi(api);
        if (null == service) {
            Flush.flush(routerRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), true);
        }
        service.setGroup(StringUtil.isEmpty(group) ? "dev" : group);
        service.setVersion(StringUtil.isEmpty(version) ? "1.0.0" : version);

        if (Auth.auth(service, routerRequest)) {
            DubboRequest dubboRequest = new DubboRequest();
            dubboRequest.setLanguage(language);
            dubboRequest.setCurrency(currency);
            // 具体参数实现,约定只能使用BaseRequestDTO的实现类。
            Object[] dubboParam = new Object[]{initParam(routerRequest)};
            dubboRequest.setData(dubboParam);
            //请求参数处理
            if (1 == service.getMulti()) {
                // 子线程初始化线程语言  dubbo请求对象初始化时，如果有语言参数也会进行初始化
                new I18nContext(language);
                ioworker.execute(() -> {
                    Object result1 = dubboInvoke.invoke(service, dubboRequest);
                    Flush.flush(routerRequest, JsonUtil.toJsonStr(result1), false);
                });
            } else {
                Object result = dubboInvoke.invoke(service, dubboRequest);
                Flush.flush(routerRequest, JsonUtil.toJsonStr(result), false);
            }
        } else {
            Flush.flush(routerRequest, JsonUtil.toJsonStr(ResultGenerator.genFailResult()), true);
        }
    }
}
