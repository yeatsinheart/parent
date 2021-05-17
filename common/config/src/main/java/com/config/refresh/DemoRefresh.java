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
public class DemoRefresh {
    //Cron表达式范例：
    //每隔5秒执行一次：*/5 * * * * ?
    //每隔1分钟执行一次：0 */1 * * * ?
    //每天23点执行一次：0 0 23 * * ?
    //每天凌晨1点执行一次：0 0 1 * * ?
    //每月1号凌晨1点执行一次：0 0 1 1 * ?
    //每月最后一天23点执行一次：0 0 23 L * ?
    //每周星期天凌晨1点实行一次：0 0 1 ? * L
    //在26分、29分、33分执行一次：0 26,29,33 * * * ?
    //每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?


    //1.cron是设置定时执行的表达式，如 0 0/5 * * * ?每隔五分钟执行一次
    //2.zone表示执行时间的时区
    //3.fixedDelay 和fixedDelayString 表示一个固定延迟时间执行，上个任务完成后，延迟多长时间执行
    //4.fixedRate 和fixedRateString表示一个固定频率执行，上个任务开始后，多长时间后开始执行
    //5.initialDelay 和initialDelayString表示一个初始延迟时间，第一次被调用前延迟的时间

    //必须等上一次任务完成才会调用下一次任务
    @Async
    @Scheduled(cron = "*/5 * * * * ?")
    public void demo() {
        log.info("demo refresh in {}", LocalDateTimeUtil.utc8());
    }
}
