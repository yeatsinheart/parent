package com.gateway.dubbo;

import com.base.i18n.I18nContext;
import lombok.Data;

@Data
public class DubboRequest {
    private String language;
    private String currency;
    private Object[] data;

    public void setLanguage(String language) {
        this.language = language;
        // 设置线程多语言
        new I18nContext(language);
    }
}
