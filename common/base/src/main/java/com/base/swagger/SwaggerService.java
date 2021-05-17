package com.base.swagger;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SwaggerService {
    private String tag;
    private List<SwaggerOperation> operations = new ArrayList<>();

    public void addOperation(SwaggerOperation operation) {
        operations.add(operation);
    }
}
