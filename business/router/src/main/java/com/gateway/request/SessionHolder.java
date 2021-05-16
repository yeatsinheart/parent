package com.gateway.request;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

public class SessionHolder {
    //属性名称：握手处理器
    public static final AttributeKey<WebSocketServerHandshaker> HAND_SHAKE_ATTR = AttributeKey.valueOf("HAND_SHAKE");
    //属性名称：websocket自定义id
    public static final AttributeKey<Boolean> KEEP_ALIVE = AttributeKey.valueOf("KEEP_ALIVE");
    public static final AttributeKey<String> PROTO = AttributeKey.valueOf("PROTO");
    public static final AttributeKey<String> USER = AttributeKey.valueOf("USER");
    public static final AttributeKey<String> URI = AttributeKey.valueOf("URI");
    public static final AttributeKey<String> IP = AttributeKey.valueOf("IP");

    public static void setIP(Channel session) {
        InetSocketAddress address = (InetSocketAddress) session.remoteAddress();
        String ip = address.getAddress().getHostAddress();
        session.attr(SessionHolder.IP).set(ip);
    }

    public static void setUri(Channel session, String uri) {
        session.attr(SessionHolder.URI).set(uri);
    }

    public static String getUri(Channel session) {
        return session.attr(SessionHolder.URI).get();
    }

    public static String getIP(Channel session) {
        return session.attr(SessionHolder.IP).get();
    }

    public static void setKeeplive(Channel session, boolean flag) {
        session.attr(SessionHolder.KEEP_ALIVE).set(flag);
    }

    public static boolean getKeeplive(Channel session) {
        return session.attr(SessionHolder.KEEP_ALIVE).get();
    }

    public static void setProto(Channel session, String args) {
        session.attr(SessionHolder.PROTO).set(args);
    }

    public static String getProto(Channel session) {
        return session.attr(SessionHolder.PROTO).get();
    }

    public static String getsession(Channel session) {
        return "[" + getProto(session) + "][" + getIP(session) + "]" + session.id().asShortText()+"=>"+getUri(session);
    }
}
