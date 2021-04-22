package com.common.result;

import com.common.utils.I18nUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 响应码枚举，参考HTTP状态码的语义
 * 解决不同业务返回需要跳转不同页面的需求
 *  比如未登录。无权限。未设置支付秘密，未绑定银行卡
 */
public enum ResultCode {
    SUCCESS(1, "SUCCESS"),//成功
    FAIL(0, "访问失败"),//失败
    UNAUTHORIZED(401, "签名错误"),//未认证（签名错误）
    NOT_FOUND(404, "此接口不存在"),//接口不存在
    TOEKNUNVALIBLE(405, "登录失效"),//未认证（token错误）
    INVALID_PARAM(10000, "参数错误"),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500, "系统繁忙,请稍后再试");
    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage(String language) {
        return StringUtils.isEmpty(I18nUtil.getString("common",message,language))?
                message:
                I18nUtil.getString("common",message,language);
    }
}
