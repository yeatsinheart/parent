package com.gateway.dubbo.updater;

import com.gateway.dubbo.caller.CallerCache;
import com.gateway.dubbo.meta.MetadataCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CacheUpdater {
    @Resource
    private MetadataCollector metadataCollector;
    @Resource
    private CallerCache callerCache;

    @Scheduled(cron = "*/5 * * * * ?")
    public void update() {
        //查找所有api文件
        //更新所有dubbo调用缓存
    }
}
