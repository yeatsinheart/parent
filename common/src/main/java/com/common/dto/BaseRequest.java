package com.common.dto;

import com.common.utils.LocalDateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

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
     * ip
     */
    private String ip;

    /**
     * 操作人用户ID
     */
    private Integer opUserId;

    /**
     * 操作人用户名
     */
    private String opUserName;

    /**
     * 请求标志
     */
    private String requestCode;

    /**
     * 请求终端 H5 ADMIN
     */
    private String client;
    /**
     * 语言code
     */
    private String language;

    /**
     * 币种code
     */
    private String currency;

    /**
     * 分页页数
     */
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;

    private Long opTime =LocalDateTimeUtil.timestamp13();

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}