package com.common.dto;

import com.common.utils.JsonUtil;
import com.common.utils.LocalDateTimeUtil;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.Serializable;

@Tag(name = "通用请求对象")
@Getter
@Setter
@NoArgsConstructor
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -4902869489789908551L;
    /**
     * 域名
     */
    @Parameter(name = "domain", description = "域名", example = "baidu.com", required = false, hidden = true)
    private String domain;
    /**
     * 站点id
     */
    @Parameter(name = "appId", description = "站点id", example = "1", required = false, hidden = true)
    private Integer appId;
    /**
     * 操作人用户ID
     */
    @Parameter(name = "requestUserId", description = "操作人用户ID", example = "1", required = false, hidden = true)
    private Integer requestUserId;
    /**
     * 操作人用户名
     */
    @Parameter(name = "op", description = "操作人用户名", example = "张三", required = false, hidden = true)
    private String requestUserName;
    /**
     * 操作
     */
    @Parameter(name = "op", description = "操作", example = "操作名", required = false, hidden = true)
    private String op;
    /**
     * 请求标志*随机码*请求链路跟踪
     */
    @Parameter(name = "requestCode", description = "请求标志*随机码*请求链路跟踪：", example = "34g43t4", required = true)
    private String requestCode;

    /**
     * 请求终端 H5 ADMIN
     */
    @Parameter(name = "", description = "来源客户端：", example = "PC", required = true)
    private String requestClient;
    /**
     * 操作人IP
     */
    @Parameter(name = "requestIp", description = "操作人IP", example = "127.0.0.1", required = false, hidden = true)
    private String requestIp;
    /**
     * 语言code
     */
    @Parameter(name = "requestLanguage", description = "语言code：", example = "zh_CN", required = true)
    private String requestLanguage;

    /**
     * 币种code
     */
    @Parameter(name = "requestCurrency", description = "币种code：", example = "CNY", required = true)
    private String requestCurrency;

    /**
     * 分页页数
     */
    @Parameter(name = "requestPageNum", description = "分页页数：", example = "1", required = true)
    private Integer requestPageNum = 1;
    /**
     * 每页数量
     */
    @Parameter(name = "requestPageSize", description = "每页数量：", example = "10", required = true)
    private Integer requestPageSize = 10;
    @Parameter(name = "requestTime", description = "请求时间", example = "2020-02-02 00:00:00.000", required = true)
    private Long requestTime = LocalDateTimeUtil.timestamp13();

    @SneakyThrows
    public String toString() {
        //return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        return JsonUtil.toJsonStr(this);
    }


}