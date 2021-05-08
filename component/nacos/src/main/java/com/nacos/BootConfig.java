package com.nacos;

import com.nacos.properties.Dubbo;
import com.nacos.properties.Mysql;
import com.nacos.properties.Redis;

public class BootConfig {
    public static void main(String[] args) {
        InitNacosConfig.put(
                Dubbo.properties + Redis.properties + Mysql.properties, "properties",
                "application",
                "DEFAULT_GROUP",
                "",
                "localhost:8848"
        );
    }
}
