package com.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.gen.controller",
        "com.gen.services",
        "com.db.config",
        "com.common.annotation"
})
@SpringBootApplication
public class GenWebWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GenWebWebApplication.class, args);
    }

}
