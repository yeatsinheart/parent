package com.config.properties;

public class Dubbo {

    public static String get(String connect, String group, String version) {
        return "dubbo.scan.base-packages=com.gateway.Notify,service.user.services.impls\n" +
                "dubbo.registry.address=nacos://" + connect + "\n" +
                "#dubbo.monitor.address=" + connect + "\n" +

                "dubbo.protocol.name=dubbo\n" +
                "dubbo.protocol.port=-1\n" +
                "## Dubbo Registry ?backup=\n" +
                "dubbo.registry.file=${spring.application.name}.dubbo.cache.log\n" +
                "# group,version\n" +
                "dubbo.registry.group=" + group + "\n" +
                "dubbo.provider.group=${dubbo.registry.group}\n" +
                "dubbo.consumer.group=${dubbo.registry.group}\n" +
                "dubbo.registry.version=" + version + "\n" +
                "dubbo.provider.version=${dubbo.registry.version}\n" +
                "dubbo.consumer.version=${dubbo.registry.version}\n" +
                "# 立马暴露\n" +
                "dubbo.registry.register=true\n" +
                "dubbo.provider.delay=-1\n" +
                "dubbo.registry.subscribe=true\n" +
                "dubbo.registry.simplified=true\n" +
                "# 不重试\n" +
                "dubbo.consumer.check=false\n" +
                "dubbo.provider.retries=0\n" +
                "dubbo.consumer.retries=0\n" +
                "#超时设定 消费端方法级>提供端方法级>消费端接口级>提供端接口级>消费端全局>提供端端全局\n" +
                "dubbo.provider.timeout=1000\n" +
                "dubbo.consumer.timeout=1000\n" +
                "#传输大小 128K\n" +
                "dubbo.provider.payload=128000\n" +
                "#dubbo.registry.use-as-metadata-center=false\n";
    }
}
