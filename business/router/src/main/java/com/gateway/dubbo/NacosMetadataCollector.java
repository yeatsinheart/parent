package com.gateway.dubbo;

import com.common.utils.JsonUtil;
import com.nacos.util.NacosUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.metadata.definition.model.FullServiceDefinition;
import org.apache.dubbo.metadata.definition.model.MethodDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class NacosMetadataCollector implements MetadataCollector {
    @Value("${dubbo.registry.group:dev}")
    private String group;
    @Value("${nacos.config.server-addr:localhost:8848}")
    private String url;
    @Value("${nacos.config.namespace:public}")
    private String namespace;

    public String getProviderMetaData(String module, String interfaze, String group, String version) {
        try {
            //每2秒更新一次？
            //api.user.services.TestService:::consumer:user-web
            //api.user.services.TestService:::provider:netty-gateway
            //String dataid = key.getServiceInterface();
            return NacosUtil.get(interfaze + ":" + version + ":" + group + ":" + CommonConstants.PROVIDER_SIDE + ":" + module, group, namespace, url);
            // return configService.getConfig(key.getUniqueKey(KeyTypeEnum.UNIQUE_KEY), group, 1000 * 2);
        } catch (Exception e) {
            log.warn("Failed to get  from nacos, cause: {}", e);
        }
        return null;
    }

    @Override
    public String[] getParamsTypes(String module, String interfaze, String group, String version, String methodName, int paramLen) {

        //MetadataIdentifier identifier = new MetadataIdentifier(interfaze, version, group, CommonConstants.PROVIDER_SIDE, module);
        String metadata = getProviderMetaData(module, interfaze, group, version);
        FullServiceDefinition serviceDefinition = JsonUtil.toObj(metadata, FullServiceDefinition.class);
        if (serviceDefinition == null) {
            return null;
        }
        List<MethodDefinition> methods = serviceDefinition.getMethods();
        if (methods != null) {
            for (MethodDefinition m : methods) {
                if (m.getName().equals(methodName) && m.getParameterTypes().length == paramLen) {
                    return m.getParameterTypes();
                }
            }
        }
        return null;
    }
}
