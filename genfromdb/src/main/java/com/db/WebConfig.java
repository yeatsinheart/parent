package com.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cheng
 * @className: WebConfig
 * @description: 静态资源配置类
 * @dateTime 2018/4/19 17:59
 */
//@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer  {

    /**
     * 日志管理
     */
    private Logger log = LoggerFactory.getLogger(WebConfig.class);

    /**
     * @description:
     * @author cheng
     * @dateTime 2018/4/19 17:59
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("配置静态资源所在目录");
        // 和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
