package com.common.result;

import com.common.utils.I18nUtil;
import org.apache.commons.lang3.StringUtils;

public enum TransLatorKey {
    成功("success"),
    系统繁忙请稍后再试("bussy"),
    调用失败("fail");
    private String key;
    TransLatorKey(String content) {
        this.key = content;
    }

    public String getContent(String module,String language) {
        String translated = I18nUtil.getString(module, key, language);
        return StringUtils.isEmpty(translated) ? key : translated;
    }
}
