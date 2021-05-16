package com.config.configs;

public class Gateway {
    public static String get() {
        return
                "## 会话保留时间 秒\n" +
                        "session.online.time=15\n" +
                        "session.idle.time=10\n" +
                        "## 网络协议\n" +
                        "net.http.ports=80\n" +
                        "net.tcp.ports=8901\n" +
                        "net.udp.ports=7901\n" +
                        "\n" +
                        "netty.read.idle.time=15\n" +
                        "netty.wirte.idle.time=20\n" +
                        "netty.readwirte.idle.time=25\n" +
                        "netty.boss.num=0\n" +
                        "netty.worker.num=0\n";
    }
}
