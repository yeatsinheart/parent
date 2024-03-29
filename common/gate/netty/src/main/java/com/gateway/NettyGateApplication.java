package com.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan(basePackages = {
        "com.gateway.server",
        "com.gateway.project",
        "com.gateway.request",
        "com.gateway.dubbo",
        "com.config.refresh",

        "service.global.services",
        "db.global.daos.impls",
        "service.tenant.services",
        "db.tenant.daos.impls",
        "service.user.services",
        "db.user.daos.impls",
        "service.cost.services",
        "db.cost.daos.impls",
        "service.money.services",
        "db.money.daos.impls",
        "service.game.services",
        "db.game.daos.impls",
        "service.chat.services",
        "db.chat.daos.impls",
        "service.bus.services",
        "db.bus.daos.impls",
        "service.report.services",
        "db.report.daos.impls",

        "com.nacos.config",
        "com.db.config",
        "com.redis",
        "com.base.pool",
        "com.base.annotation",
        "com.base.event",
})
@EnableScheduling
@Slf4j
public class NettyGateApplication {
    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NettyGateApplication.class, args);
    }
}
