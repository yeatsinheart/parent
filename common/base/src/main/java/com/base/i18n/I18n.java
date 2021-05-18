package com.base.i18n;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = JacksonI18n.class)
@JacksonAnnotationsInside
public @interface I18n {
    /**
     * 属性值指定多语言模块
     *
     * @return
     */
    String module() default "";

}
