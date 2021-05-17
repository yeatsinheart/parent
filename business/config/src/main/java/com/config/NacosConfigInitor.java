package com.config;

import com.config.properties.*;
import com.nacos.util.NacosUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NacosConfigInitor {

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
    public static String nacosServerUrl = "localhost:8848";
    //public static String serverAddr = "172.20.10.4:8848";
    //public static String serverAddr = "192.168.15.100:8848";

    public static void main(String[] args) {
        //get("app", GROUP, null, serverAddr);
        //put("test=test", "properties", "app", GROUP, "test", serverAddr);
        NacosUtil.putConfig(
                Spring.get(),
                "properties",
                "spring",
                GROUP,
                NAME_SPACE,
                nacosServerUrl
        );
        NacosUtil.putConfig(
                Gateway.get(),
                "properties",
                "gateway",
                GROUP,
                NAME_SPACE,
                nacosServerUrl
        );
        NacosUtil.putConfig(
                Dubbo.get(nacosServerUrl, "dev", "1.0.0"),
                "properties",
                "dubbo",
                GROUP,
                NAME_SPACE,
                nacosServerUrl
        );
        NacosUtil.namespace(nacosServerUrl, "" + NAME_SPACE + "-dubbo-service");
        NacosUtil.namespace(nacosServerUrl, "" + NAME_SPACE + "-dubbo-parameter");
        NacosUtil.namespace(nacosServerUrl, "" + NAME_SPACE + "-dubbo-monitor");

        NacosUtil.putConfig(
                Redis.get("47.242.219.77", "6379", "1WKaajAB1Qd3Jro8"),
                "properties",
                "redis",
                GROUP,
                NAME_SPACE,
                nacosServerUrl
        );
        NacosUtil.putConfig(
                Mysql.get("localhost:3306/code", "root", "zdc1991"),
                "properties",
                "mysql",
                GROUP,
                NAME_SPACE,
                nacosServerUrl
        );

    }


}
