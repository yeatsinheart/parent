package com.maven.multity.service;

import com.base.utils.FileUtil;
import com.base.utils.StringUtil;
import com.maven.multity.BaseProperties;
import com.maven.multity.BaseRunner;

import java.io.File;

public class ServiceFile {

    public static String level = "service";

    public static void init(String projectpath, String project) {
        //初始化文件夹结构
        String src = projectpath + File.separator + "src";
        FileUtil.mkdir(src + File.separator + "test" + File.separator + "java" + File.separator + level + File.separator + project);
        String base = src + File.separator + "main";

        String basePackage = base + File.separator + "java" + File.separator + level + File.separator + project;
        String starter = basePackage + File.separator + StringUtil.firstUpper(project) + StringUtil.firstUpper(level) + "Application.java";
        FileUtil.write(starter, BaseRunner.starter(level,project), true);

        String services = basePackage + File.separator + "services";
        String servicesimpls = services + File.separator + "impls";
        String testService = servicesimpls + File.separator + "TestServiceImpl.java";
        FileUtil.write(testService, testServiceImpl(project), true);

        String baseResource = base + File.separator + "resources";
        FileUtil.mkdir(baseResource);

        String resources = baseResource + File.separator + "application.properties";
        FileUtil.write(resources, resource(project), true);

    }
    public static String resource(String project) {
        return BaseProperties.genProperties(project + "-" + level, 0)
                ;
    }
    public static String datasql(String project) {
        return "SELECT 1 FROM DUAL;";
    }

    public static String testServiceImpl(String project) {
        return "package " + level + "." + project + ".services.impls;\n" +
                "import com.base.constant.Language;\n" +
                "import com.base.result.ResultGenerator;\n\n" +
                "import api." + project + ".services.TestService;\n" +
                "import com.alibaba.nacos.api.config.annotation.NacosValue;\n" +
                "import org.apache.dubbo.config.annotation.DubboService;\n" +
                "import lombok.extern.slf4j.Slf4j;\n" +
                "import com.base.result.Result;\n" +
                "@Slf4j\n" +
                "@DubboService\n" +
                "public class TestServiceImpl implements TestService {\n" +
                "    @NacosValue(value = \"${testvalue}\",autoRefreshed = true)\n" +
                "    private String nacosvalue;\n" +
                "\n" +
                "    @Override\n" +
                "    public Result<String> test(String test) {\n" +
                "        return ResultGenerator.genSuccessResult(\"当前输入\"+test+\"，nacos中的值是\"+nacosvalue,Language.中文.getCode());\n" +
                "    }\n" +
                "}\n";

    }

}
