package com.gateway.request;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

public class SessionHolder {
    //属性名称：握手处理器
    public static final AttributeKey<WebSocketServerHandshaker> HAND_SHAKE_ATTR = AttributeKey.valueOf("HAND_SHAKE");
    //属性名称：websocket自定义id
    public static final AttributeKey<String> PROTO = AttributeKey.valueOf("PROTO");
    public static final AttributeKey<String> USER = AttributeKey.valueOf("USER");
    public static final AttributeKey<String> URI = AttributeKey.valueOf("URI");
    public static final AttributeKey<String> IP = AttributeKey.valueOf("IP");

    public static void setIP(Channel session) {
        InetSocketAddress address = (InetSocketAddress) session.remoteAddress();
        String ip = address.getAddress().getHostAddress();
        session.attr(SessionHolder.IP).set(ip);
    }

    public static void setProto(Channel session, String args) {
        session.attr(SessionHolder.PROTO).set(args);
    }

    public static String getsession(Channel session) {
        return session.attr(IP).get() + ":" + session.attr(USER).get();
    }
}
