package com.gateway.dubbo.meta;

import com.base.utils.JsonUtil;
import com.base.utils.NacosUtil;
import com.gateway.dubbo.caller.RemoteApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.metadata.definition.model.FullServiceDefinition;
import org.apache.dubbo.metadata.definition.model.MethodDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class NacosMetadataCollector implements MetadataCollector {
    private final ConcurrentHashMap<String, String[]> cachedService = new ConcurrentHashMap<>();
    @Value("${dubbo.registry.group:dev}")
    private String group;
    @Value("${nacos.config.server-addr:localhost:8848}")
    private String nacosUrl;
    @Value("${nacos.config.namespace:public}")
    private String namespace;

    public String getProviderMetaData(RemoteApi service) {
        try {
            //每2秒更新一次？
            String config = NacosUtil.getConfig(service.getNacosDataId(), service.getGroup(), namespace + "-dubbo-parameter", nacosUrl);
            if (StringUtils.isEmpty(config)) {
                return null;
            }
            return config;
        } catch (Exception e) {
            log.warn("Failed to get  from nacos, cause: {}", e);
            return null;
        }
    }

    @Override
    public String[] getParamsTypes(RemoteApi service) {
        String[] paramsType = cachedService.get(service.toString());
        if (null == paramsType) {
            String metadata = getProviderMetaData(service);
            FullServiceDefinition serviceDefinition = JsonUtil.toObj(metadata, FullServiceDefinition.class);
            if (serviceDefinition == null) {
                return null;
            }
            List<MethodDefinition> methods = serviceDefinition.getMethods();
            if (!CollectionUtils.isEmpty(methods)) {
                for (MethodDefinition m : methods) {
                    // && m.getParameterTypes().length == paramLen
                    if (m.getName().equals(service.getMethodName())) {
                        cachedService.putIfAbsent(service.toString(),m.getParameterTypes());
                        paramsType = m.getParameterTypes();
                        return paramsType;
                    }
                }
            }
        }
        return null;
    }
    public void setCache(){

    }
    @Override
    public boolean clear() {
         cachedService.clear();
        return true;
    }
}
