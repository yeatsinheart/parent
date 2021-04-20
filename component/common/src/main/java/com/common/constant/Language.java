package com.common.constant;

public enum  Language {
    /**
     * 程序种的语言编码，文件后缀编码
     *
     ISO639-1编码-语言变体
     */
    中文("zh-CN"),
    英文("en-US"),
    台湾("zh-TW"),
    越南("vi-VN");
    private String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Language getLanguage(String code) {
        for (Language now : Language.values()) {
            if (now.code.equals(code)) {
                return now;
            }
        }
        //默认中文
        return 中文;
    }

    public static String getFileCodeByCode(String code) {
        Language[] languageEnums = Language.values();
        for (int i = 0; i < languageEnums.length; i++) {
            if (languageEnums[i].code.equals(code)) {
                return languageEnums[i].code;
            }
        }
        return 中文.code;
    }
}
