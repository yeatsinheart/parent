package com.gateway.router;


public interface Router {
    String ROUTER_KEY = "gateway_route_";

    void handle(GateRequest gateRequest);
}
