package com.base.i18n;

import com.base.utils.I18nUtil;
import com.base.utils.StringUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

/**
 * Jackson返回数据进行脱敏
 * 配合@Desensitization使用
 */
@Log4j2
public class JacksonI18n extends JsonSerializer<String> implements ContextualSerializer {
    private String module;

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        try {
            String i18nValue = I18nUtil.getString(module, value, I18nContext.getLanguage());
            jsonGenerator.writeString(StringUtil.isEmpty(i18nValue)?value:i18nValue);
        } catch (Exception e) {
            log.warn("i18n has no field {}", value);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty)
            throws JsonMappingException {
        module = beanProperty.getAnnotation(I18n.class).module();
        return this;
    }
}

