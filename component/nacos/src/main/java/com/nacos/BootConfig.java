package com.nacos;

import com.nacos.properties.Dubbo;
import com.nacos.properties.Mysql;
import com.nacos.properties.Redis;

public class BootConfig {
    public static void main(String[] args) {

        // todo &符号不能搞啊
        InitNacosConfig.put(
                Dubbo.properties + Redis.properties + Mysql.properties, "properties",
                "app",
                "DEFAULT_GROUP",
                "",
                "localhost:8848"
        );
    }
}
