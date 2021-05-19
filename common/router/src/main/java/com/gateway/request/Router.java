package com.gateway.request;


public interface Router {
    String ROUTER_KEY = "gateway_project_route_";

    void handle(GateRequest gateRequest);
}
