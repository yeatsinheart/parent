package com.common.desensitization;

import lombok.Data;

import java.io.Serializable;

@Data
public class DesensitizeDTO implements Serializable {
    @Desensitization(type = DesensitionType.IDENTITYNO)
    private String identityNo;
    @Desensitization(type = DesensitionType.ACCOUNT)
    private String name;
    @Desensitization(type = DesensitionType.REALNAME)
    private String realname;
}
