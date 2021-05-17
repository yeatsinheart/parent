package com.gateway.dubbo;

import com.base.utils.JsonUtil;
import com.base.utils.NacosUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.metadata.definition.model.FullServiceDefinition;
import org.apache.dubbo.metadata.definition.model.MethodDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

    public String getProviderMetaData(DubboRemoteService service,String group, String version) {
        String dataId = service.getInterfaceName() + ":" + version + ":" + group + ":" + CommonConstants.PROVIDER_SIDE + ":" + service.getModule();
        try {
            //每2秒更新一次？
            //api.user.services.TestService:::consumer:user-web
            //api.user.services.TestService:::provider:netty-gateway
            //String dataid = key.getServiceInterface();
            log.info("nacos服务器获取" + dataId);
            String config = NacosUtil.getConfig(dataId, group, namespace + "-dubbo-parameter", url);
            if (StringUtils.isEmpty(config)) {
                return null;
            }
            return config;
            // return configService.getConfig(key.getUniqueKey(KeyTypeEnum.UNIQUE_KEY), group, 1000 * 2);
        } catch (Exception e) {
            log.warn("Failed to get  from nacos, cause: {}", e);
            return null;
        }
    }

    @Override
    public String[] getParamsTypes(DubboRemoteService service, String group, String version) {

        //MetadataIdentifier identifier = new MetadataIdentifier(interfaze, version, group, CommonConstants.PROVIDER_SIDE, module);
        String metadata = getProviderMetaData(service, group, version);
        FullServiceDefinition serviceDefinition = JsonUtil.toObj(metadata, FullServiceDefinition.class);
        if (serviceDefinition == null) {
            return null;
        }
        List<MethodDefinition> methods = serviceDefinition.getMethods();
        if (!CollectionUtils.isEmpty(methods)) {
            for (MethodDefinition m : methods) {
                // && m.getParameterTypes().length == paramLen
                if (m.getName().equals(service.getMethodName())) {
                    return m.getParameterTypes();
                }
            }
        }
        return null;
    }
}
