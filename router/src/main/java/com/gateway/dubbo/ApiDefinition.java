package com.gateway.dubbo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.dubbo.common.utils.StringUtils;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiDefinition {
    /**
     * 应用名
     */
    private String module;
    /**
     * 接口名
     */
    private String interfaceName;
    /**
     * 版本号
     */
    private String version;
    /**
     * 分组
     */
    private String group;
    /**
     * 接口名：版本号
     */
    private String serviceID;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数列表
     */
    private List<Object> data;
    /**
     * 参数类型列表
     */
    private List<String> paramTypes;

    public String getServiceID() {
        serviceID = interfaceName;
        if (!StringUtils.isBlank(group)) {
            serviceID = group + "/" + serviceID;
        }
        if (!StringUtils.isBlank(version)) {
            serviceID = serviceID + ":" + version;
        }
        return serviceID;
    }
}
