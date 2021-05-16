package com.nacos.properties;

public class Application {
    String properties = "" +
            "dubbo.scan.base-packages=service.demo.services.impls,service.testb.services.impls,com.gateway.Notify,service.user.services.impls\n" +
            "dubbo.consumer.check=false\n" +
            "dubbo.protocol.name=dubbo\n" +
            "dubbo.protocol.port=-1\n" +
            "\n" +
            "## Dubbo Registry ?backup=\n" +
            "dubbo.registry.address=nacos://172.20.10.4:8848\n" +
            "#dubbo.monitor.address=192.168.0.102:8848\n" +
            "dubbo.registry.file=${spring.application.name}.dubbo.cache.log\n" +
            "# 暂时失效，只能用注册中心的group,version\n" +
            "dubbo.registry.group=dev1\n" +
            "dubbo.provider.group=${dubbo.registry.group}\n" +
            "dubbo.consumer.group=${dubbo.registry.group}\n" +
            "dubbo.registry.version=11\n" +
            "dubbo.provider.version=${dubbo.registry.version}\n" +
            "dubbo.consumer.version=${dubbo.registry.version}\n" +
            "\n" +
            "\n" +
            "#立马暴露\n" +
            "dubbo.registry.register=true\n" +
            "dubbo.provider.delay=-1\n" +
            "dubbo.registry.subscribe=true\n" +
            "dubbo.registry.simplified=true\n" +
            "#不重试\n" +
            "dubbo.provider.retries=0\n" +
            "dubbo.consumer.retries=0\n" +
            "#超时设定 消费端方法级>提供端方法级>消费端接口级>提供端接口级>消费端全局>提供端端全局\n" +
            "dubbo.provider.timeout=1000\n" +
            "dubbo.consumer.timeout=1000\n" +
            "#传输大小 128K\n" +
            "dubbo.provider.payload=128000\n" +
            "#dubbo.registry.use-as-metadata-center=false\n" +
            "# Redis服务器地址\n" +
            "spring.redis.host=47.242.219.77\n" +
            "# Redis服务器连接端口\n" +
            "spring.redis.port=6379\n" +
            "# Redis服务器连接密码（默认为空）\n" +
            "spring.redis.password=1WKaajAB1Qd3Jro8\n" +
            "mybatis-plus.mapper-locations.classpath*=mappers/*.xml\n" +
            "# 配置文件指定执行sql（静态资源）\n" +
            "# spring.datasource.initialization-mode=always\n" +
            "# schema.sql中一般存放的是DDL脚本\n" +
            "# spring.datasource.schema = classpath*:schema.sql\n" +
            "# data.sql中一般存放的是DQL（数据查询）脚本或DML（数据操作）脚本\n" +
            "spring.datasource.data =  classpath*:datasql.sql\n" +
            "#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver\n" +
            "#该插件有性能损耗，不建议生产环境使用。\n" +
            "#spring.datasource.driver-class-name=com.p6spy.engine.spy.P6SpyDriver\n" +
            "# 北京时间东八区serverTimezone=GMT%2B8 UTC代表的是全球标准时间 默认使用全球标准时间。# 服务器传入的时间会被格式化为serverTimezone 所以使用时间戳存时间最好\n" +
            "#spring.datasource.url=jdbc:mysql://47.242.219.77:3306/chzx_chat?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true\n" +
            "#spring.datasource.url=jdbc:p6spy:mysql://47.242.219.77:3306/chzx_chat?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true\n" +
            "#spring.datasource.username=root\n" +
            "#spring.datasource.password=IQdtJcwVuspR0WT6\n" +
            "\n" +
            "\n" +
            "spring.shardingsphere.datasource.names=code\n" +
            "\n" +
            "spring.shardingsphere.datasource.code.type=com.zaxxer.hikari.HikariDataSource\n" +
            "spring.shardingsphere.datasource.code.driver-class-name=com.mysql.cj.jdbc.Driver\n" +
            "spring.shardingsphere.datasource.code.jdbc-url=jdbc:mysql://localhost:3306/code?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true\n" +
            "spring.shardingsphere.props.sql.show=true\n" +
            "#spring.shardingsphere.datasource.code.url=jdbc:mysql://localhost:3306/code\n" +
            "spring.shardingsphere.datasource.code.username=root\n" +
            "spring.shardingsphere.datasource.code.password=zdc1991\n" +
            "\n" +
            "spring.shardingsphere.sharding.tables.user.actual-data-nodes=code.user\n" +
            "spring.shardingsphere.sharding.tables.user.table-strategy.complex.sharding-columns=id,tenant_id\n" +
            "spring.shardingsphere.sharding.tables.user.table-strategy.complex.algorithm-class-name=com.db.sharding.strategy.Complex\n" +
            "\n" +
            "testvalue=value1";
}
