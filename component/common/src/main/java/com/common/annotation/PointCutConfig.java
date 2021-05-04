package com.common.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCutConfig {
    // 指定注解
    @Pointcut("@annotation(com.common.annotation.Role)")
    public void role() {
    }
}
