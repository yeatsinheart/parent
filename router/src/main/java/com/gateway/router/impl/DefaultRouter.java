package com.gateway.router.impl;

import com.common.dto.BaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.gateway.router.Router;

@Slf4j
@Component("router_default")
public class DefaultRouter implements Router {
    @Override
    public String handle(BaseRequest request) {
        return "router_default";
    }
}
