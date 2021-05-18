package com.gateway.dubbo.meta;

import com.gateway.dubbo.caller.RemoteApi;

public interface MetadataCollector {
    String[] getParamsTypes(RemoteApi service);

    boolean clear();
}
