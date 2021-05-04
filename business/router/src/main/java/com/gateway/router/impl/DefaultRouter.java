package com.gateway.router.impl;

import com.gateway.router.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("gateway_route_default")
public class DefaultRouter implements Router {
    @Override
    public String handle(String request) {
        return "router_default";
    }
}
