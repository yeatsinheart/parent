package com.config.properties;

public class Gateway {
    public static String get() {
        return
                "## 会话保留时间 秒\n" +
                        "session.online.time=15\n" +
                        "session.idle.time=10\n" +
                        "\n" +
                        "netty.read.idle.time=15\n" +
                        "netty.wirte.idle.time=20\n" +
                        "netty.readwirte.idle.time=25\n" +
                        "\n" +
                        "## 项目业务-》接口性能分析单核单线程情况下cpu时间与等待时间占比\n" +
                        "netty.socket.num=1\n" +
                        "netty.handle.num=1\n" +
                        "netty.call.num=1\n" +
                        "## 项目业务-》请求报文格式（加密信息/json）-》网络协议-》端口\n" +
                        "net.http.ports=80\n" +
                        "net.tcp.ports=8901\n" +
                        "net.udp.ports=7901\n";
    }
}
