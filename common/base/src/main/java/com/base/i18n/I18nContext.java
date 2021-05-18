package com.base.i18n;

public class I18nContext implements AutoCloseable {
    static final ThreadLocal<String> i18n = new ThreadLocal<>();
    public I18nContext(String language) {
        i18n.set(language);
    }
    public static String getLanguage() {
        return i18n.get();
    }
    @Override
    public void close() {
        i18n.remove();
    }
}