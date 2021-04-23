package com.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.gen.controller",
        "com.gen.services",
        "com.db.config",
})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GenWebWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GenWebWebApplication.class, args);
    }

}
