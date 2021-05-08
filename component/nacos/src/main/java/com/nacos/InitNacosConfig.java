package com.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.common.utils.HttpUtils;
import com.properties.DubboProperties;
import com.properties.MysqlProperties;
import com.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class InitNacosConfig {

    /**
     * 各应用启动后，
     * 当nacos中没有配置时，初始化对应的nacos配置信息  config
     * 当nacos中存在值时，进行更新操作 config
     * <p>
     * 启动配置；env，
     * 其它配置：config
     */
    public static String GROUP = "DEFAULT_GROUP";
    public static String NAME_SPACE = "public";
    //public static String serverAddr = "172.20.10.4:8848";
    //public static String serverAddr = "192.168.15.100:8848";
    public static String serverAddr = "localhost:8848";

    public static void main(String[] args) {
        get("app", GROUP, null, serverAddr);
        put("test=test", "properties", "app", GROUP, "test", serverAddr);
    }

    // 获取配置
    public static String get(String dataId, String group, String namespaceId, String url) {
        String config = HttpUtils.get("http://" + url + "/nacos/v1/cs/configs?dataId=" + dataId + "&group=" + group + "&tenant=" + namespaceId + "", null);
        System.out.println(config);
        if ("config data not exist".equals(config)) {
            return null;
        }
        return config;
    }

    public static String put(String content, String type, String dataId, String group, String namespaceId, String url) {
        Map<String, Object> params = new HashMap<>();
        params.put("tenant", namespaceId);
        params.put("dataId", dataId);
        params.put("group", group);
        params.put("content", content);
        params.put("type", type);
        String putted = HttpUtils.postByForm("http://" + url + "/nacos/v1/cs/configs" + "", params, null);
        System.out.println(putted);
        if ("false".equals(putted)) {
            return null;
        }
        return putted;
    }

    public static void remove(String dataId, String group, ConfigService configService) {
        try {
            boolean isRemoveOk = configService.removeConfig(dataId, group);
        } catch (NacosException e) {
            log.error("{}", e);
        }
    }

}
