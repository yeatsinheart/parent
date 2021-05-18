package com.gateway.dubbo.caller;

import com.base.utils.JsonUtil;
import lombok.Data;

@Data
public class RemoteApi {
    public static final int MULTY_THREAD = 1;
    private String module = "netty-gateway1";
    private String interfaceName = "api.global.services.TestService";
    private String methodName = "test";

    private String group;
    private String version;


    private Integer multi = 0;

    public String getNacosDataId() {
        return this.getInterfaceName() + ":" + this.getVersion() + ":" + this.getGroup() + ":provider:" + this.getModule();
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonStr(this);
    }
}
