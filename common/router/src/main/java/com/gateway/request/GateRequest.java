package com.gateway.request;

import com.base.i18n.I18nContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.Data;

import java.util.Map;

/**
 * 路由请求
 */
@Data
public class GateRequest {
    private ChannelHandlerContext ctx;
    /**
     * 请求来源域名
     * */
    private String domain;
    /**
     * 请求来源协议
     */
    private String protocal;
    /** 请求路径，用来识别具体router业务路由 */
    private String uri;
    /** 执行语言  有多语言要求时，该参数需要初始化 */
    private String language;
    /** 执行币种  有多币种要求时，该参数需要初始化 */
    private String currency;
    /** GET POST K-V数据 */
    private Map<String, Object> params;
    /** HTTP 独有请求头 */
    private HttpHeaders headers;
    /** 请求时间 */
    private Long requestTime;
    private Long createTime = System.currentTimeMillis();
    private Long responseTime;
    private String response;

    public void setLanguage(String language) {
        this.language = language;
        // 设置线程多语言
        new I18nContext(language);
    }
}
