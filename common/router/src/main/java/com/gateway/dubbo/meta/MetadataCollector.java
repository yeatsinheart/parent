package com.gateway.dubbo.meta;

import com.gateway.dubbo.caller.DubboRemoteService;

public interface MetadataCollector {
    String[] getParamsTypes(DubboRemoteService service);
    boolean clear();
}
