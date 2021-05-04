package com.common.annotation;

import com.common.constant.Language;
import com.common.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class GlobalAopAdvice {

    /*
    @Aspect	切面声明，标注在类、接口（包括注解类型）或枚举上。
    如果存在同一个类中调用相关方法的，请先用@Autowried注入自己，然后再用注入的属性调用方法，避免内部类无法调用
@Pointcut	切入点声明，即切入到哪些目标类的目标方法。value 属性指定切入点表达式，默认为 ""，用于被通知注解引用，这样通知注解只需要关联此切入点声明即可，无需再重复写切入点表达式

@Before	前置通知, 在目标方法(切入点)执行之前执行。value 属性绑定通知的切入点表达式，可以关联切入点声明，也可以直接设置切入点表达式
注意：如果在此回调方法中抛出异常，则目标方法不会再执行，会继续执行后置通知 -> 异常通知。

@After	后置通知, 在目标方法(切入点)执行之后执行

@AfterReturning	返回通知, 在目标方法(切入点)返回结果之后执行，在 @After 的后面执行pointcut 属性绑定通知的切入点表达式，优先级高于 value，默认为 "" 强处理可以访问到方法的返回值，但它不可以改变目标方法的返回值。

@AfterThrowing	异常通知, 在方法抛出异常之后执行, 意味着跳过返回通知pointcut 属性绑定通知的切入点表达式，优先级高于 value，默认为 ""
注意：如果目标方法自己 try-catch 了异常，而没有继续往外抛，则不会进入此回调函数

@Around	环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
    * */

    // 拦截，打印日志，并且通过JoinPoint 获取方法参数
    @Before("com.common.annotation.PointCutConfig.role()")
    public void logBeforeSvc(JoinPoint joinPoint) {
        log.info("在service 方法执行前 打印第 1 次日志");
        log.info("拦截的service 方法的方法签名: " + joinPoint.getSignature());
        log.info("拦截的service 方法的方法入参: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "result"
            , pointcut = "com.common.annotation.PointCutConfig.role()")
    public void log(Object result) {
        log.info("获取目标方法返回值:" + result);
        log.info("模拟记录日志功能...");
    }

    @Around("com.common.annotation.PointCutConfig.role()")
    public Object role(ProceedingJoinPoint joinPoint) {
        System.out.println("===================第一个切面===================：" + System.currentTimeMillis());
        //获取请求参数，详见接口类
        Object[] objects = joinPoint.getArgs();
        /*
        Long id = ((JSONObject) objects[0]).getLong("id");
        String name = ((JSONObject) objects[0]).getString("name");
        System.out.println("id1->>>>>>>>>>>>>>>>>>>>>>" + id);
        System.out.println("name1->>>>>>>>>>>>>>>>>>>>>>" + name);
        */
        // id小于0则抛出非法id的异常
        /*if (id < 0) {
            return JsonUtil.toObj("{\"message\":\"illegal id\",\"code\":403}");
        }*/
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("role 拦截 ERR:{}", throwable);
        }
        return ResultGenerator.genFailResult(Language.中文.getCode());
    }

}
