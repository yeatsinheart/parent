package com.base.swagger;

import lombok.Data;

import java.util.List;

@Data
public class SwaggerOperation {
    List<SwaggerParameter> responses;
    List<SwaggerParameter> parameters;
    private String operationId;
    private String summary;
}
