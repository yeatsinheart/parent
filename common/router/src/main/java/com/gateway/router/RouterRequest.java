package com.gateway.router;

import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

import java.util.Map;
@Data
public class RouterRequest {
    private ChannelHandlerContext ctx;
    private String uri;
    private Map<String, Object> params;
    private Map<String, Object> headers;
    // 请求时间
    private Long createTime = System.currentTimeMillis();
}
