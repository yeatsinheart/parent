package com.gateway.dubbo;

import lombok.Data;

@Data
public class DubboRequest {
    private String language;
    private String currency;
    private Object[] data;
}
