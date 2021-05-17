package com.config.refresh;

import com.base.utils.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@EnableAsync
public class ApiRefresh {
    @Async
    @Scheduled(cron = "*/5 * * * * ?")
    public void demo() {
        log.info("ApiRefresh refresh in {}", LocalDateTimeUtil.utc8());
    }
}
