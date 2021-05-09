package com.common.swagger;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SwaggerParameter {
    private String tag;
    private String name;
    private String description;
    private String example;
    private boolean required;
    private boolean hidden;
    private List<SwaggerParameter> inner = new ArrayList<>();
}
