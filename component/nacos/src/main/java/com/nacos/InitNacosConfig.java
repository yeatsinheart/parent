package com.nacos;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.common.utils.HttpUtils;
import com.nacos.properties.Dubbo;
import com.nacos.properties.Mysql;
import com.nacos.properties.Redis;
import com.nacos.util.NacosUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
        //get("app", GROUP, null, serverAddr);
        //put("test=test", "properties", "app", GROUP, "test", serverAddr);
        NacosUtil.put(
                Dubbo.properties + Redis.properties + Mysql.properties,
                "properties",
                "app",
                "DEFAULT_GROUP",
                "",
                "localhost:8848"
        );
    }





}
