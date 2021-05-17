package com.maven;

import com.base.utils.FileUtil;

import java.util.Arrays;
import java.util.List;

public class AllInGate {
    public static void main(String[] args) {
        List<String> modules = Arrays.asList(
                "global", "tenant", "user",
                "cost", "money", "game", "chat",
                "bus",
                "report"
        );
        String business = "business";
        AllInOneUtil.all(modules,business);
        FileUtil.write("./common/gate/netty/src/main/resources/application.properties","spring.application.name=netty-gateway\n" +
                "# 日志颜色\n" +
                "spring.output.ansi.enabled=always\n" +
                "#logging.level.root=debug\n" +
                "logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint}%clr(${LOG_LEVEL_PATTERN:%5p}) %clr(${PID}){magenta}%clr([%t]){faint} %clr(%-40.40logger{39}){cyan}[line:%line]%clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:%wEx}\n" +
                "#开启配置预加载功能 从nacos中读取配置文件\n" +
                "nacos.config.bootstrap.enable=true\n" +
                "# 主配置服务器地址 \n" +
                "nacos.config.server-addr=localhost:8848\n" +
                "#nacos.config.context-path=nacos \n" +
                "nacos.config.data-ids=gateway,spring,dubbo,redis,mysql\n" +
                "nacos.config.namespace=dev\n" +
                "nacos.config.group=DEFAULT_GROUP\n" +
                "nacos.config.type=properties \n" +
                "nacos.config.max-retry=10\n" +
                "# 主配置 开启自动刷新 \n" +
                "nacos.config.auto-refresh=true\n" +
                "# 主配置 重试时间 \n" +
                "nacos.config.config-retry-time=2333\n" +
                "# 主配置 配置监听长轮询超时时间 \n" +
                "nacos.config.config-long-poll-timeout=46000\n" +
                "# 主配置 开启注册监听器预加载配置服务（除非特殊业务需求，否则不推荐打开该参数） \n" +
                "nacos.config.enable-remote-sync-config=true",true);

        FileUtil.write("./common/router/pom.xml","<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>common</artifactId>\n" +
                "        <groupId>code</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>router</artifactId>\n" +
                "\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>code."+business+"</groupId>\n" +
                "            <artifactId>all</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>code</groupId>\n" +
                "            <artifactId>config</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "            <!--提供编译而不参与打包-->\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>" +
                "        <dependency>\n" +
                "            <groupId>code</groupId>\n" +
                "            <artifactId>base</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "            <!--提供编译而不参与打包-->\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>code</groupId>\n" +
                "            <artifactId>dubbo</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "            <!--提供编译而不参与打包-->\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "</project>",true);

        FileUtil.remove(".idea");
    }
}
