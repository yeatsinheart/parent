package com.common.event.springevent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SpringExampleListener implements ApplicationListener<SpringExampleEvent> {
    // 异步处理
    @Async
    @Override
    public void onApplicationEvent(SpringExampleEvent springExampleEvent) {
        log.info("监听到事件--邮箱地址:" + springExampleEvent);

        //模拟处理的耗时3s
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("事件处理完成");
    }
}
