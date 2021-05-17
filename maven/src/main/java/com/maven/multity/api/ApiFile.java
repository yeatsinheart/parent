package com.maven.multity.api;

import com.base.utils.FileUtil;

import java.io.File;

public class ApiFile {

    public static String level = "api";

    public static void init(String projectpath, String project) {
        //初始化文件夹结构
        String src = projectpath + File.separator + "src";
        FileUtil.mkdir(src + File.separator + "test" + File.separator + "java" + File.separator + level + File.separator + project);
        String base = src + File.separator + "main";

        String basePackage = base + File.separator + "java" + File.separator + level + File.separator + project;
        String dtos = basePackage + File.separator + "dtos";
        String constants = basePackage + File.separator + "constants";
        String services = basePackage + File.separator + "services";
        String testService = services + File.separator + "TestService.java";
        FileUtil.mkdir(dtos);
        FileUtil.mkdir(constants);
        FileUtil.write(testService, testService(project), true);

    }

    public static String testService(String project) {
        return "package " + level + "." + project + ".services;\n" +
                "import com.base.result.Result;\n\n" +
                "public interface TestService {\n" +
                "    Result<String> test(String test);\n" +
                "}\n";

    }
}
