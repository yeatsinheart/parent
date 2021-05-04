package com.common.event.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
//内部实现方式是观察者模式，可以解耦业务系统之间的业务，提供系统的可拓展性、复用性以及可维护性。
//是ApplicationListener的子类，能够实现有序监听
@Slf4j
@Component
public class SpringExampleSmartListener implements SmartApplicationListener {
    /**
     *  指定支持哪些类型的事件
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == SpringExampleEvent.class;
    }
    /**
     *  指定支持发生事件所在的类型
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }
    //return为“2”，优先级将靠后 同步下监听执行顺序 -- 数值越低优先级越高
    @Override
    public int getOrder() {
        return 2;
    }
    @Async//同步与异步分别存在，优先级失效
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // 事件执行失败，重复执行机制
        SpringExampleEvent registerEvent = (SpringExampleEvent) applicationEvent;
        log.info("监听到事件--邮箱地址:" + registerEvent);

        //模拟处理的耗时3s
        //如果内部异常，会不会影响后续的监听呢。。。
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("事件处理完成");
    }
}
