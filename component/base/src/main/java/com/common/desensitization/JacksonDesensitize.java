package com.common.desensitization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

/**
 * Jackson返回数据进行脱敏
 * 配合@Desensitization使用
 */
@Log4j2
public class JacksonDesensitize extends JsonSerializer<String> implements ContextualSerializer, DesensitizeService {
    private DesensitionType type;
    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (type != null) {
            try {
                List<String> rules = this.desensitize(type, null);
                if (rules.size() > 1) {
                    String match = rules.get(0);
                    String result = rules.get(1);
                    if (null != match && result != null && match.length() > 0) {
                        jsonGenerator.writeString(value.replaceAll(match, result));
                    }
                }
            } catch (Exception e) {
                log.warn("JacksonDesensitize has no field {}", value);
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty)
            throws JsonMappingException {
        type = beanProperty.getAnnotation(Desensitization.class).type();
        return this;
    }
}

