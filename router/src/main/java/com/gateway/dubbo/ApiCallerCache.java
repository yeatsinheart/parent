package com.gateway.dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class ApiCallerCache {

    @Autowired
    private Registry registry;
    private  ApplicationConfig applicationConfig;

    @PostConstruct
    public void init() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registry.getUrl().getProtocol() + "://" + registry.getUrl().getAddress());
        registryConfig.setGroup(registry.getUrl().getParameter(org.apache.dubbo.common.constants.CommonConstants.GROUP_KEY));
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName("netty-gateway");
        applicationConfig.setRegistry(registryConfig);
    }
    // 缓存调用关系
    private final ConcurrentHashMap<String, ReferenceConfig<GenericService>> cachedConfig = new ConcurrentHashMap<>();

    public ReferenceConfig<GenericService> get(String interfaceName,String group, String version) {
        ReferenceConfig<GenericService> reference;
        String cachedKey = group+"/"+interfaceName + ":" + version;
        reference = cachedConfig.get(cachedKey);
        if (reference == null) {
            ReferenceConfig<GenericService> newReference = initCaller(interfaceName, group, version);
            ReferenceConfig<GenericService> oldReference = cachedConfig.putIfAbsent(cachedKey, newReference);
            if (oldReference != null) {
                reference = oldReference;
            } else {
                reference = newReference;
            }
        }
        return reference;
    }

    public  ReferenceConfig<GenericService> initCaller(String interfaceName, String group, String version) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setGeneric("raw.return");
        reference.setApplication(applicationConfig);
        reference.setGroup(group);
        reference.setVersion(version);
        reference.setInterface(interfaceName);
        return reference;
    }

}
