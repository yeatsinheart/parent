package com.gateway.dubbo.caller;

import lombok.Data;

@Data
public class DubboRemoteService {
    public static final int MULTY_THREAD = 1;
    private String module = "netty-gateway1";
    private String interfaceName = "api.global.services.TestService";
    private String methodName = "test";

    private String group;
    private String version;


    private Integer multi = 0;

    public String getNacosDataId() {
        String dataId = this.getInterfaceName() + ":" + this.getVersion() + ":" + this.getGroup() + ":provider:" + this.getModule();
        return dataId;
    }

    @Override
    public String toString() {
        return "DubboRemoteService{" +
                "module='" + module + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", group='" + group + '\'' +
                ", version='" + version + '\'' +
                ", multi=" + multi +
                '}';
    }
}
