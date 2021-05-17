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
    private final ConcurrentHashMap<Integer, DubboRemoteService> cachedService = new ConcurrentHashMap<>();
    @Value("${dubbo.registry.group:register-group}")
    private String group;
    @Value("${nacos.config.server-addr}")
    private String address;
    private ApplicationConfig applicationConfig;

    @PostConstruct
    public void init() {
        log.info(address + "===" + group);
        URL url = URL.valueOf("nacos://" + address);
        log.error(url.toString());
        if (StringUtils.isNotEmpty(group)) {
            url = url.addParameter(CommonConstants.GROUP_KEY, group);
        }
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(url);
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registry.getUrl().getProtocol() + "://" + registry.getUrl().getAddress());
        registryConfig.setGroup(registry.getUrl().getParameter(CommonConstants.GROUP_KEY));
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName("netty-gateway");
        applicationConfig.setRegistry(registryConfig);
    }


    public DubboRemoteService get(Integer api, String group, String version) {
        DubboRemoteService service;
        service = cachedService.get(api);
        log.info("缓存的服务信息{},本次查找{},找到的为{}", cachedService, api, service);
        if (service == null) {
            DubboRemoteService newService = initCaller(api, group, version);
            DubboRemoteService oldService = cachedService.putIfAbsent(api, newService);
            if (oldService != null) {
                service = oldService;
            } else {
                service = newService;
            }
        }
        return service;
    }

    public DubboRemoteService initCaller(Integer api, String group, String version) {
        DubboRemoteService service = new DubboRemoteService();
        ApiCache.get(api);
        ReferenceConfig<GenericService> invoker = new ReferenceConfig<>();
        invoker.setGeneric("raw.return");
        invoker.setApplication(applicationConfig);
        invoker.setGroup(group);
        invoker.setVersion(version);
        invoker.setInterface(interfaceName);
        service.setInvoker(invoker);
        return service;
    }

}
