package com.gateway.auth;

import com.gateway.dubbo.caller.RemoteApi;
import com.gateway.project.GateRequest;

public class Auth {
    public static boolean auth(RemoteApi remoteApi, GateRequest gateRequest) {
        // 全站信息，
        // 维护时间记录如果有多条，且若有重叠部分
        // 是否在全站维护时间
        // 站点信息
        // 站点是否可用，
        // 如果维护，是否在维护周期中，维护时间记录如果有多条，且若有重叠部分
        // 是否加解密。。。。
        // 是否需要登陆，校验请求中的登陆信息
        // 是否拥有权限，校验用户的权限
        // 参数是否过滤
        return true;
    }

    public boolean inMainten(GateRequest gateRequest) {
        long start = 0;
        long end = 0;
        // 在维护时间内
        return gateRequest.getCreateTime() < start
                && gateRequest.getCreateTime() > end;
    }
}