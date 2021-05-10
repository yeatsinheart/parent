package com.common.utils;

import com.common.dto.BaseRequest;
import com.common.result.Result;
import com.common.swagger.SwaggerOperation;
import com.common.swagger.SwaggerParameter;
import com.common.swagger.SwaggerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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
        className = "com.common.utils.SwaggerUtil";
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
                if (!StringUtils.isEmpty(operation.getSummary())) {
                    service.addOperation(operation);
                }
            }
            System.out.println(JsonUtil.toJsonStr(service));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void responseAnnotation(Class response) {
        //范型
    }

    public static List<SwaggerParameter> modules(Class module) {
        List<SwaggerParameter> params = new ArrayList<>();
        // Field[] fields = module.getFields();
        List<Field> fields = new ArrayList<>();
        Class tempClass = module;
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Parameter) {
                    Parameter parameterAnnotation = (Parameter) annotation;
                    SwaggerParameter parameter = new SwaggerParameter();
                    parameter.setName(field.getName());
                    parameter.setDescription(parameterAnnotation.description());
                    parameter.setRequired(parameterAnnotation.required());
                    if (parameterAnnotation.hidden()) {
                        continue;
                    }
                    parameter.setExample(parameterAnnotation.example());
                    parameter.setType(field.getType().getSimpleName());
                    Schema schema = parameterAnnotation.schema();
                    Class<?> implementation = schema.implementation();
                    if (!implementation.equals(Void.class)) {
                        List<SwaggerParameter> inner = modules(implementation);
                        if (!CollectionUtils.isEmpty(inner)) {
                            parameter.setInner(inner);
                        } else {
                            parameter.setType(implementation.getSimpleName());
                        }
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
            parameter.setExample(parameterAnnotation.example());
            Schema schema = parameterAnnotation.schema();
            Class<?> implementation = schema.implementation();
            if (!implementation.equals(Void.class)) {
                List<SwaggerParameter> inner = modules(implementation);
                if (!CollectionUtils.isEmpty(inner)) {
                    parameter.setInner(inner);
                } else {
                    parameter.setType(implementation.getSimpleName());
                }
            }
            params.add(parameter);
        }
        return params;
    }

    public static void methodAnnotation(Method method, SwaggerOperation operation) {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Operation) {
                Operation operationAnnotation = (Operation) annotation;
                String operationId = operationAnnotation.operationId();
                String summary = operationAnnotation.summary();
                operation.setOperationId(operationId);
                operation.setSummary(summary);
                if (operationAnnotation.hidden()) {
                    continue;
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 限定只能一个对象参数哦
                for (Class parameterType : parameterTypes) {
                    List<SwaggerParameter> inner = modules(parameterType);
                    if (!CollectionUtils.isEmpty(inner)) {
                        operation.setParameters(inner);
                    }
                }
                if (CollectionUtils.isEmpty(operation.getParameters())) {

                }

                //获取泛型信息
                Type type = method.getGenericReturnType();
                System.out.println("返回参数" + JsonUtil.toJsonStr(type));
                try {
                    ParameterizedType pType = (ParameterizedType) type;
                    if (pType.getRawType().getTypeName().equals(Result.class.getTypeName())) {
                        List<SwaggerParameter> results = modules(Result.class);
                        Type[] types = pType.getActualTypeArguments();
                        List<SwaggerParameter> data = null;
                        for (Type type2 : types) {
                            System.out.println("泛型具体类型" + type2);//class java.lang.String
                            Class tar = Class.forName(type2.getTypeName());
                            data = modules(tar);
                            //class java.lang.Object   泛型类型
                            for (SwaggerParameter result : results) {
                                if (result.getName().equals("data")) {
                                    if (CollectionUtils.isEmpty(data)) {
                                        result.setType(type2.getTypeName());
                                    } else {
                                        result.setInner(data);
                                    }
                                }
                            }
                        }

                        operation.setResponses(results);
                    }
                } catch (Exception e) {

                }


/*
                Parameter[] parameters = operationAnnotation.parameters();
                operation.setParameters(paramAnnotation(parameters));
                ApiResponse[] responses = operationAnnotation.responses();*/
            }
        }
    }

    @Operation(operationId = "2", summary = "测试")
    public static Result<BaseRequest> test(BaseRequest args) {
        return null;
    }

    @Operation(operationId = "1", summary = "查找所有api文件")
    public static void listAllFiles(File file, String module, String suffix) {
        if (file.isFile()) {
            if (StringUtils.isEmpty(suffix)) {
                files.add(file);
            } else if (file.getAbsolutePath().contains("-" + module) && file.getName().endsWith(suffix)) {
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
