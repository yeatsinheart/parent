package com.gateway.dubbo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.metadata.definition.model.FullServiceDefinition;
import org.apache.dubbo.metadata.definition.model.MethodDefinition;
import org.apache.dubbo.metadata.report.identifier.KeyTypeEnum;
import org.apache.dubbo.metadata.report.identifier.MetadataIdentifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

import static com.alibaba.nacos.api.PropertyKeyConst.SERVER_ADDR;

@Slf4j
@Component
public class NacosMetadataCollector implements MetadataCollector {
    private final static String DEFAULT_ROOT = "dubbo";
    private ConfigService configService;
    @Value("${dubbo.registry.group:register-group}")
    private String group;
    @Value("${nacos.config.server-addr:localhost:8848}")
    private URL url;

    @PostConstruct
    public void init() {
        group = url.getParameter(CommonConstants.GROUP_KEY, DEFAULT_ROOT);
        Properties nacosProperties = new Properties();
        String serverAddr = url.getHost() + ":" + url.getPort();
        nacosProperties.put(SERVER_ADDR, serverAddr);
        try {
            configService = NacosFactory.createConfigService(nacosProperties);
        } catch (Exception e) {
            log.error("nacos配置中心连接失败", e);
        }
    }

    public String getProviderMetaData(MetadataIdentifier key) {
        try {
            //每2秒更新一次？
            return configService.getConfig(key.getUniqueKey(KeyTypeEnum.UNIQUE_KEY), group, 1000 * 2);
        } catch (NacosException e) {
            log.warn("Failed to get " + key + " from nacos, cause: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String[] getParamsTypes(String module, String interfaze, String group, String version, String methodName, int paramLen) {

        MetadataIdentifier identifier = new MetadataIdentifier(interfaze, version, group, CommonConstants.PROVIDER_SIDE, module);
        String metadata = getProviderMetaData(identifier);
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
