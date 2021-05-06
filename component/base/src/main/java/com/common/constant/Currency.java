package com.common.constant;

public enum Currency {
    CNY("CNY"),
    TEST("TEST");
    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public static Currency getCurrency(String code) {
        for (Currency now : Currency.values()) {
            if (now.code.equals(code)) {
                return now;
            }
        }
        // 默认人民币
        return CNY;
    }

    public String getCode() {
        return code;
    }

}
