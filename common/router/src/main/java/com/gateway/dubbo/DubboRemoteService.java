package com.gateway.dubbo;

import lombok.Data;

@Data
public class DubboRemoteService {
    private String module = "netty-gateway";
    private String interfaceName = "api.global.services.TestService";
    private String methodName = "test";
    private String group = "dev";
    private String version = "1.0.0";

    @Override
    public String toString() {
        return "DubboRemoteService{" +
                "module='" + module + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", group='" + group + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
