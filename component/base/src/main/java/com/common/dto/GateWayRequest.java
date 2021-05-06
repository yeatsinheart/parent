package com.common.dto;

import com.common.utils.JsonUtil;
import com.common.utils.LocalDateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class GateWayRequest implements Serializable {
    private static final long serialVersionUID = -490286948934908551L;
    /**
     * 域名
     */
    private String domain;
    /**
     * 操作人IP
     */
    private String requestIp;
    /**
     * 路由标签
     */
    private String uri;
    /**
     * 操作接口
     */
    private String api;
    /**
     * 操作人用户token
     */
    private Integer requestToken;
    /**
     * 请求标志*随机码*请求链路跟踪
     */
    private String requestCode;
    /**
     * 请求终端 H5 ANDROID IOS ADMIN MANAGE
     */
    private String requestClient;
    /**
     * 语言code
     */
    private String requestLanguage;

    /**
     * 币种code
     */
    private String requestCurrency;

    /**
     * 真实请求数据
     */
    private BaseRequest data;

    private Long requestTime = LocalDateTimeUtil.timestamp13();

    public static void main(String[] args) {
        System.out.println(new GateWayRequest());
    }

    @SneakyThrows
    public String toString() {
        //return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        return JsonUtil.toJsonStr(this);
    }
}