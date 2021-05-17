package com.base.desensitization;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = JacksonDesensitize.class)
@JacksonAnnotationsInside
public @interface Desensitization {
    /**
     * 脱敏规则类型
     *
     * @return
     */
    DesensitionType type();

    /**
     * 附加值, 自定义正则表达式等
     *
     * @return
     */
    String[] attach() default "";
}
