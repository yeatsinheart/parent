package com.config.configs;

public class Redis {
    public static final String properties = "# Redis服务器地址\n" +
            "spring.redis.host=47.242.219.77\n" +
            "# Redis服务器连接端口\n" +
            "spring.redis.port=6379\n" +
            "# Redis服务器连接密码（默认为空）\n" +
            "spring.redis.password=1WKaajAB1Qd3Jro8\n";

    public static String get(String host, String port, String pwd) {
        return "# Redis服务器地址\n" +
                "spring.redis.host=" + host + "\n" +
                "# Redis服务器连接端口\n" +
                "spring.redis.port=" + port + "\n" +
                "# Redis服务器连接密码（默认为空）\n" +
                "spring.redis.password=" + pwd + "\n";

    }
}
