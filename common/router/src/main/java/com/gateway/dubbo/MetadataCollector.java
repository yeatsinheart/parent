package com.gateway.dubbo;

public interface MetadataCollector {
    String[] getParamsTypes(DubboRemoteService service);
}
