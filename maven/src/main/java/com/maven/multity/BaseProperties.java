package com.maven.multity;

public class BaseProperties {
    public static String genProperties(String name, int port) {
        return "spring.application.name="+name+"\n" +
                "# 日志颜色\n" +
                "spring.output.ansi.enabled=always\n" +
                "#logging.level.root=debug\n" +
                "logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint}%clr(${LOG_LEVEL_PATTERN:%5p}) %clr(${PID}){magenta}%clr([%t]){faint} %clr(%-40.40logger{39}){cyan}[line:%line]%clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:%wEx}\n" +
                "\n" +
                "\n" +
                "#开启配置预加载功能 从nacos中读取配置文件\n" +
                "nacos.config.bootstrap.enable=true\n" +
                "# 主配置服务器地址 \n" +
                "nacos.config.server-addr=localhost:8848\n" +
                "#nacos.config.context-path=nacos \n" +
                "nacos.config.data-ids=spring,dubbo,redis,mysql\n" +
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
                "nacos.config.enable-remote-sync-config=true";
    }
}
