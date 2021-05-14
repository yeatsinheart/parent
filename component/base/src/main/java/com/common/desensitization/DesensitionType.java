package com.common.desensitization;

public enum DesensitionType {
    /**
     * 手机号脱敏
     * "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}"
     * "(\\d{3})\\d{4}(\\d{4})"
     */
    PHONE("mobile", "11位手机号", "(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{4}(\\d{4}}", "$1****$2"),
    IDENTITYNO("identityNo", "15或者18身份证号", "(\\w{4})\\w{7,10}(\\w{4})", "$1****$2"),
    BANKCARDNO("bankCardNo", "银行卡号", "(\\d{4})\\d*(\\d{4})", "$1****$2"),
    REALNAME("realname", "真实姓名Json类型", "(.{1})(.*)(.{0})", "$1玩家"),
    ACCOUNT("account", "长度在6位以上", "(.{2})(.*)(.{2})", "$1**$3"),
    CUSTOM("custom", "自定义正则处理", ""),
    TRUNCATE("truncate", "字符串截取处理，前后各保留多少位", "2", "2"),
    ;
    String type;
    String describe;
    String[] rule;

    DesensitionType(String type, String describe, String... rule) {
        this.type = type;
        this.describe = describe;
        this.rule = rule;
    }

    public String getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }

    public String[] getRule() {
        return rule;
    }

}
