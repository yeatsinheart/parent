package com.gateway.dubbo;

import lombok.Data;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;

@Data
public class DubboRemoteService {
    private String module = "netty-gateway";
    private String interfaceName = "api.global.services.TestService";
    private String methodName = "test";
    private ReferenceConfig<GenericService> invoker;
}
