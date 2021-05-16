package com.nacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

@Slf4j
@Component
public class LoggingConfig extends AbstractNacosConfig {
    public final static String DATA_ID = "logging-config";
    @Resource
    private LoggingSystem loggingSystem;

    /**
     * 实现根据nacos参数更新运行时内容的方法
     */
    @Override
    public boolean initByValue(Properties properties) {
        String name = (String) properties.getOrDefault("logging.level.name", "logging.level.name");
        String level = (String) properties.getOrDefault("logging.level","info");
        LogLevel logLevel = LogLevel.INFO;
        try {
            logLevel = LogLevel.valueOf(level.toUpperCase());
        } catch (Exception e) {
            log.error(DATA_ID + "配置存在问题{},{}", properties, e);
        }
        loggingSystem.setLogLevel(name, logLevel);
        log.error("设置日志级别{},{}", name, logLevel);
        return true;
    }

    @Override
    public String defaultProperties() {
        Properties properties = new Properties();
        properties.put("logging.level.name", "root");
        properties.put("logging.level", "info");
        return properties.toString();
    }

}

