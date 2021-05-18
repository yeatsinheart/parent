package com.base.event.springevent;

import com.base.utils.NamingThreadFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {
    /**
     * 获取异步线程池执行对象
     */
    @Override
    public Executor getAsyncExecutor() {
        /*// 使用Spring内置线程池任务对象
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置线程池参数
        executor.initialize();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);*/

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new NamingThreadFactory("SpringAsyncConfig.java"));
        executor.prestartAllCoreThreads();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
