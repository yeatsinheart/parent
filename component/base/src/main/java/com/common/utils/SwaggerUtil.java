package com.common.utils;

import com.common.swagger.SwaggerOperation;
import com.common.swagger.SwaggerParameter;
import com.common.swagger.SwaggerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "swagger文档工具类")
public class SwaggerUtil {
    private static final List<File> files = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir"));
        System.out.println(file.getAbsolutePath());
        // 在api项目中的找所有的service定义
        listAllFiles(file, "api", ".java");

        for (File f : files) {
            // service-api-parame
            interfaceGen(f);
        }
    }

    public static void interfaceGen(File f) {
        String fileName = f.getAbsolutePath();
        String className = fileName.substring(fileName.indexOf("main/java") + 10).replaceAll("/", ".").replaceAll(".java", "");
        String project = className.split("\\.")[1];
        className = "com.common.utils.SwaggerUtis";
        try {
            SwaggerService service = new SwaggerService();
            Class<?> clazz = Class.forName(className);
            Annotation[] tags = clazz.getAnnotations();
            for (Annotation annotation : tags) {
                if (annotation instanceof Tag) {
                    String tag = ((Tag) annotation).name();
                    service.setTag(tag);
                }
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                SwaggerOperation operation = new SwaggerOperation();
                methodAnnotation(method, operation);
                service.addOperation(operation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void responseAnnotation(ApiResponse[] responses) {

    }

    public static List<SwaggerParameter> modules(Class module) {
        List<SwaggerParameter> params = new ArrayList<>();
        Field[] fields = module.getFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Parameter) {
                    Parameter parameterAnnotation = (Parameter) annotation;
                    SwaggerParameter parameter = new SwaggerParameter();
                    parameter.setName(parameterAnnotation.name());
                    parameter.setDescription(parameterAnnotation.description());
                    parameter.setRequired(parameterAnnotation.required());
                    parameter.setHidden(parameterAnnotation.hidden());
                    parameter.setExample(parameterAnnotation.example());
                    Schema schema = parameterAnnotation.schema();
                    Class<?> implementation = schema.implementation();
                    if (!implementation.equals(Void.class)) {
                        SwaggerParameter innerparameter = new SwaggerParameter();
                        parameter.setInner(modules(implementation));
                    }
                    params.add(parameter);
                }
            }


        }

        return params;
    }

    public static List<SwaggerParameter> paramAnnotation(Parameter[] parameters) {
        List<SwaggerParameter> params = new ArrayList<>();
        for (Parameter parameterAnnotation : parameters) {
            parameterAnnotation.example();
            SwaggerParameter parameter = new SwaggerParameter();
            parameter.setName(parameterAnnotation.name());
            parameter.setDescription(parameterAnnotation.description());
            parameter.setRequired(parameterAnnotation.required());
            parameter.setHidden(parameterAnnotation.hidden());
            parameter.setExample(parameterAnnotation.example());
            Schema schema = parameterAnnotation.schema();
            Class<?> implementation = schema.implementation();
            if (!implementation.equals(Void.class)) {
                SwaggerParameter innerparameter = new SwaggerParameter();
                parameter.setInner(modules(implementation));
            }
            params.add(parameter);
        }
        return params;
    }

    public static void methodAnnotation(Method method, SwaggerOperation operation) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Operation) {
                System.out.println(method);
                Operation operationAnnotation = (Operation) annotation;
                String operationId = operationAnnotation.operationId();
                String summary = operationAnnotation.summary();
                String description = operationAnnotation.description();
                boolean hidden = operationAnnotation.hidden();
                operation.setOperationId(operationId);
                operation.setSummary(summary);
                operation.setDescription(description);
                operation.setHidden(hidden);

                Parameter[] parameters = operationAnnotation.parameters();
                operation.setParameters(paramAnnotation(parameters));
                ApiResponse[] responses = operationAnnotation.responses();

            }
        }
    }

    @Operation(operationId = "1", summary = "查找所有api文件", description = "查找所有api文件",
            parameters = {
                    @Parameter(schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(description = "返回的是页面",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")}
    )
    public static void listAllFiles(@Parameter(name = "file", description = "位置", required = true) File file, @Parameter String module, @Parameter String suffix) {
        if (file.isFile()) {
            if (StringUtils.isEmpty(suffix)) {
                files.add(file);
            } else if (file.getAbsolutePath().contains("-" + module) && file.getName().endsWith(suffix)) {
                System.out.println(file);
                files.add(file);
            }
        } else if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                listAllFiles(file1, module, suffix);
            }
        }
    }
}
