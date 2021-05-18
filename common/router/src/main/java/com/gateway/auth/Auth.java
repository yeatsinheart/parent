package com.gateway.auth;

import com.gateway.dubbo.caller.DubboRemoteService;
import com.gateway.router.RouterRequest;

public class Auth {
    public static boolean auth(DubboRemoteService service, RouterRequest routerRequest) {
        return true;
    }
}
