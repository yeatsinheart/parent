package com.properties;

public class DubboProperties {
    public static final String properties="" +
            "dubbo.scan.base-packages=" +
                "service.demo.services.impls," +
                "service.testb.services.impls," +
                "com.gateway.Notify\n" +
            "dubbo.consumer.check=false\n" +
            "dubbo.protocol.name=dubbo\n" +
            "dubbo.protocol.port=-1\n" +
            "## Dubbo Registry ?backup=\n" +
            "dubbo.registry.address=nacos://172.20.10.4:8848\n" +
            "#dubbo.monitor.address=172.20.10.4:8848\n" +
            "dubbo.registry.file=dubbo.cache.log\n" +
            "dubbo.registry.group=register-group\n" +
            "dubbo.registry.version=1\n" +
            "dubbo.registry.subscribe=true\n" +
            "dubbo.registry.simplified=true\n" +
            "#立马暴露\n" +
            "dubbo.registry.register=true\n" +
            "dubbo.provider.delay=-1\n" +
            "#不重试\n" +
            "dubbo.provider.retries=0\n" +
            "dubbo.consumer.retries=0\n" +
            "#超时设定 消费端方法级>提供端方法级>消费端接口级>提供端接口级>消费端全局>提供端端全局\n" +
            "dubbo.provider.timeout=1000\n" +
            "dubbo.consumer.timeout=1000\n" +
            "#传输大小 128K\n" +
            "dubbo.provider.payload=128000\n" +
            "#dubbo.registry.use-as-metadata-center=false";
}
