package com.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.properties.DubboProperties;
import com.properties.MysqlProperties;
import com.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Properties;

@Slf4j
public class AInitNacosConfig {
    public static String GROUP = "DEFAULT_GROUP";
    public static String NAME_SPACE = "public";
    public static String serverAddr = "192.168.0.102:8848";

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
            //properties.put(PropertyKeyConst.NAMESPACE, NAME_SPACE);
            ConfigService configService = NacosFactory.createConfigService(properties);
            //NamingService nameService = NacosFactory.createNamingService(properties);
            application(configService);
        } catch (NacosException e) {
            log.error("{}", e);
        }
    }

    public static String get(String dataId, String group, ConfigService configService) {
        try {
            String content = configService.getConfig(dataId, group, 3000);
            if (StringUtils.isEmpty(content)) {
                content = "";
            }
            return content;
        } catch (NacosException e) {
            log.error("{}", e);
        }
        return null;
    }

    public static void remove(String dataId, String group, ConfigService configService) {
        try {
            boolean isRemoveOk = configService.removeConfig(dataId, group);
        } catch (NacosException e) {
            log.error("{}", e);
        }
    }

    public static void put(String dataId, String group, String content, ConfigService configService) {
        try {
            //log.info(dataId + group + content);
            System.out.println(get(dataId,group,configService));
            //, ConfigType.PROPERTIES.getType()
            boolean isPublishOk = configService.publishConfig(dataId, group, content);
            System.out.println(isPublishOk);
        } catch (NacosException e) {
            log.error("{}", e);
        }
    }

    public static void application(ConfigService configService) {
        put("application", GROUP,
                DubboProperties.properties + "\n" +
                        RedisProperties.properties + "\n" +
                        MysqlProperties.properties,
                configService);
    }

    public static void dubbo(ConfigService configService) {
        put("dubbo", GROUP, DubboProperties.properties, configService);
    }

    public static void redis(ConfigService configService) {
        put("redis", GROUP, RedisProperties.properties, configService);
    }

    public static void mysql(ConfigService configService) {
        put("mysql", GROUP, MysqlProperties.properties, configService);
    }
}
