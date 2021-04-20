package com.common.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.common.utils.LocalDateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -4902869489789908551L;
    /**
     * 域名
     */
    private String domain;
    /**
     * 站点id
     */
    private Integer appId;
    /**
     * 操作人用户ID
     */
    private Integer requestUserId;
    /**
     * 操作人用户名
     */
    private String requestUserName;
    /**
     * 操作
     */
    private String op;
    /**
     * 请求标志*随机码*请求链路跟踪
     */
    private String requestCode;

    /**
     * 请求终端 H5 ADMIN
     */
    private String requestClient;
    /**
     * 操作人IP
     */
    private String requestIp;
    /**
     * 语言code
     */
    private String requestLanguage;

    /**
     * 币种code
     */
    private String requestCurrency;

    /**
     * 分页页数
     */
    private Integer requestPageNum = 1;
    /**
     * 每页数量
     */
    private Integer requestPageSize = 10;

    private Long requestTime = LocalDateTimeUtil.timestamp13();

    public String toString() {
        //return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }


}