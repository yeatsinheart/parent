package com.gateway.dubbo;

public interface MetadataCollector {
    String[] getParamsTypes(String module, String interfaze, String group, String version, String methodName, int paramLen);
}
