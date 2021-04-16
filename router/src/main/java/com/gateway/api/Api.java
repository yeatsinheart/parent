package com.gateway.api;

import lombok.Data;

@Data
public class Api {
    private Integer id;
    private String name;
    private String app;
    private String service;
    private String method;
    private String params;
    private String params_example;
    private String test_response;
    private boolean canStatic;
    private Long updateTime;
    private Long lastUpdateTime;
}
