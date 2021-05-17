package com.gateway.router.impl;

import com.base.constant.Language;
import com.gateway.dubbo.DubboInvoke;
import com.gateway.router.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("gateway_route_dubbo")
public class DubboRouter implements Router {
    @Autowired
    private DubboInvoke dubboInvoke;
    @Override
    public String handle(Map<String, Object> params) {
        //{data:[{}]}
        dubboInvoke.invoke(1,"","",params, Language.中文.getCode());
        return "router_default";
    }
}

