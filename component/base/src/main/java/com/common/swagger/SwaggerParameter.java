package com.common.swagger;

import lombok.Data;

import java.util.List;

@Data
public class SwaggerParameter {
    private String tag;
    private String name;
    private String description;
    private String type;
    private String example;
    private boolean required;
    private List<SwaggerParameter> inner;
}
