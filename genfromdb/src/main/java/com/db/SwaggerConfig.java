package com.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                /*    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())*/
                .apis(RequestHandlerSelectors.basePackage("com.db.aaaaaaaaaaa.controller"))
                .paths(PathSelectors.regex(".*/dailyact/.*"))

                //.paths(PathSelectors.regex("dailyact"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact sely = new Contact("名字想好没", "https://itweknow.cn", "gancy.programmer@gmail.com");

        return new ApiInfo(
                "项目集成 Swagger 实例文档",
                " 临时处理类 ",
                "API V1.0",
                "Terms of service",
                sely,
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }
}
