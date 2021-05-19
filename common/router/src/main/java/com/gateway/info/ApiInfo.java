package com.gateway.info;

import com.base.utils.NacosUtil;

public class ApiInfo {
    public static void main(String[] args) {
        //http://localhost:8848/nacos/v1/cs/configs?dataId=&group=-dubbo-parameter&tenant=dev-dubbo-parameter
        // 遍历所有的分页接口。直接保存到本地
        NacosUtil.getConfig("","-dubbo-parameter","","");
    }
}
