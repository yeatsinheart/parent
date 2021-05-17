package com.gateway.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ApiCallerCache {
    // 缓存调用关系
    private final ConcurrentHashMap<String, ReferenceConfig<GenericService>> cachedService = new ConcurrentHashMap<>();
    @Value("${dubbo.registry.group:register-group}")
    private String group;
    @Value("${nacos.config.server-addr}")
    private String address;
    private ApplicationConfig applicationConfig;

    @PostConstruct
    public void init() {
        URL url = URL.valueOf("nacos://" + address);
        if (StringUtils.isNotEmpty(group)) {
            url = url.addParameter(CommonConstants.GROUP_KEY, group);
        }
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(url);
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registry.getUrl().getProtocol() + "://" + registry.getUrl().getAddress());
        registryConfig.setGroup(group);
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName("netty-gateway");
        applicationConfig.setRegistry(registryConfig);
    }


    public ReferenceConfig<GenericService> get(DubboRemoteService service) {
        ReferenceConfig<GenericService> invokerCache;
        invokerCache = cachedService.get(service);
        if (invokerCache == null) {
            ReferenceConfig<GenericService> newService = initCaller(service);
            ReferenceConfig<GenericService> oldService = cachedService.putIfAbsent(service.toString(), newService);
            if (oldService != null) {
                invokerCache = oldService;
            } else {
                invokerCache = newService;
            }
        }
        return invokerCache;
    }

    public ReferenceConfig<GenericService> initCaller(DubboRemoteService service) {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setGeneric("raw.return");
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setGroup(service.getGroup());
        referenceConfig.setVersion(service.getVersion());
        referenceConfig.setInterface(service.getInterfaceName());
        return referenceConfig;
    }

}
