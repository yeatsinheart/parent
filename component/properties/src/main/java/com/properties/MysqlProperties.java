package com.properties;

public class MysqlProperties {
    public static final String properties = "" +
            "mybatis-plus.mapper-locations.classpath*=mappers/*.xml\n" +
            "# 配置文件指定执行sql（静态资源）\n" +
            "# spring.datasource.initialization-mode=always\n" +
            "# schema.sql中一般存放的是DDL脚本\n" +
            "# spring.datasource.schema = classpath*:schema.sql\n" +
            "# data.sql中一般存放的是DQL（数据查询）脚本或DML（数据操作）脚本\n" +
            "spring.datasource.data =  classpath*:datasql.sql\n" +
            "#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver\n" +
            "#该插件有性能损耗，不建议生产环境使用。\n" +
            "spring.datasource.driver-class-name=com.p6spy.engine.spy.P6SpyDriver\n" +
            "# 北京时间东八区serverTimezone=GMT%2B8 UTC代表的是全球标准时间 默认使用全球标准时间。# 服务器传入的时间会被格式化为serverTimezone 所以使用时间戳存时间最好\n" +
            "#spring.datasource.url=jdbc:mysql://47.242.219.77:3306/chzx_chat?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true\n" +
            "spring.datasource.url=jdbc:p6spy:mysql://47.242.219.77:3306/chzx_chat?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true\n" +
            "spring.datasource.username=root\n" +
            "spring.datasource.password=IQdtJcwVuspR0WT6";
}
