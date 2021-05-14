package com.common.desensitization;

import com.common.utils.JsonUtil;

public class Test {
    public static void main(String[] args) {
        DesensitizeDTO dto = new DesensitizeDTO();
        dto.setName("张三");
        dto.setRealname("李四");
        dto.setIdentityNo("350421199110101035");
        System.out.println(JsonUtil.toJsonStr(dto));
    }
}
