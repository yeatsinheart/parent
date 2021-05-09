package com.common.swagger;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SwaggerOperation {
    List<SwaggerParameter> parameters = new ArrayList<>();
    private String operationId;
    private String summary;
    private String description;
    private boolean hidden;
}
