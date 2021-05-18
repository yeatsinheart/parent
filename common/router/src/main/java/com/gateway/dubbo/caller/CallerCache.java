package com.gateway.dubbo.caller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class CallerCache {
    // 缓存调用关系
    private final ConcurrentHashMap<String, ReferenceConfig<GenericService>> cachedService = new ConcurrentHashMap<>();
    @Value("${dubbo.registry.group:dev}")
    private String group;
    @Value("${dubbo.registry.address}")
    private String address;
    @Value("${spring.application.name}")
    private String app;
    private ApplicationConfig applicationConfig;

    public void clear() {
        cachedService.clear();
        initAllApi();
    }
    public void initAllApi() {
        List<DubboRemoteService> methods = new ArrayList<>();
        methods.parallelStream().forEach(method -> {
            cachedService.putIfAbsent(method.toString(), initCaller(method));
        });
    }

    @PostConstruct
    public void init() {
        /*URL url = URL.valueOf(address);
        if (StringUtils.isNotEmpty(group)) {
            url = url.addParameter(CommonConstants.GROUP_KEY, group);
        }*/
       /* RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(url);*/
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(address);
        registryConfig.setGroup(group);
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName(app);
        applicationConfig.setRegistry(registryConfig);
    }


    public ReferenceConfig<GenericService> get(DubboRemoteService service) {
        ReferenceConfig<GenericService> invokerCache;
        // 本地都需要需要250ms 所以必须要缓存起来
        invokerCache = cachedService.get(service.toString());
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
