package com.gateway.router.impl;

import com.base.constant.Language;
import com.base.utils.JsonUtil;
import com.gateway.dubbo.DubboInvoke;
import com.gateway.dubbo.HttpDubboRequest;
import com.gateway.router.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("gateway_route_default")
public class DefaultRouter implements Router {
    @Autowired
    private DubboInvoke dubboInvoke;
    @Override
    public String handle(Map<String, Object> params) {
        //{data:[{}]}
        HttpDubboRequest request = new HttpDubboRequest();
        return JsonUtil.toJsonStr(dubboInvoke.invoke(1,"dev","1.0.0",params, Language.中文.getCode()));
    }
}
