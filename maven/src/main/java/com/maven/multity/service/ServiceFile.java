package com.maven.multity.service;

import com.common.utils.FileUtil;
import com.common.utils.StringUtil;
import com.properties.BaseProperties;

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
        FileUtil.write(starter, starter(project), true);

        String services = basePackage + File.separator + "services";
        String servicesimpls = services + File.separator + "impls";
        String testService = servicesimpls + File.separator + "TestServiceImpl.java";
        FileUtil.write(testService, testServiceImpl(project), true);

        String baseResource = base + File.separator + "resources";
        FileUtil.mkdir(baseResource);

        String resources = baseResource + File.separator + "application.properties";
        FileUtil.write(resources, resource(project), true);

    }

    public static String datasql(String project) {
        return "SELECT 1 FROM DUAL;";
    }

    public static String testServiceImpl(String project) {
        return "package " + level + "." + project + ".services.impls;\n" +
                "import com.common.constant.Language;\n" +
                "import com.common.result.ResultGenerator;\n\n" +
                "import api." + project + ".services.TestService;\n" +
                "import com.alibaba.nacos.api.config.annotation.NacosValue;\n" +
                "import org.apache.dubbo.config.annotation.DubboService;\n" +
                "import lombok.extern.slf4j.Slf4j;\n" +
                "import com.common.result.Result;\n" +
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

    public static String resource(String project) {
        return BaseProperties.genProperties(project + "-" + level, 0) +
                ""
                ;
    }

    public static String starter(String project) {
        return "package " + level + "." + project + ";\n" +
                "import com.common.utils.ShutDown;\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "import org.springframework.context.ConfigurableApplicationContext;\n" +
                "import org.springframework.context.annotation.ComponentScan;\n" +
                "import org.springframework.context.annotation.EnableAspectJAutoProxy;\n" +
                "import javax.sql.DataSource;\n" +
                "import java.sql.Connection;\n" +
                "\n" +
                "@SpringBootApplication\n" +
                "@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)\n" +
                "@ComponentScan(basePackages={\n" +
                "        \"" + level + "." + project + ".services\",\n" +
                "        \"com.common.annotation\",\n" +
                "        \"com.db.config\",\n" +
                "        \"db." + project + ".daos.impls\",\n" +
                "        \"com.common.pool\",\n" +
                "        \"com.redis\"\n" +
                "})\n" +
                "public class " + StringUtil.firstUpper(project) + StringUtil.firstUpper(level) + "Application {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Runtime.getRuntime().addShutdownHook(new ShutDown());\n" +
                "        ConfigurableApplicationContext applicationContext = SpringApplication.run(" + StringUtil.firstUpper(project) + StringUtil.firstUpper(level) + "Application.class, args);\n" +
                "        try {\n" +
                "            /*String[] beans = applicationContext\n" +
                "                    .getBeanDefinitionNames();\n" +
                "            for (String beanName : beans) {\n" +
                "                Class<?> beanType = applicationContext\n" +
                "                        .getType(beanName);\n" +
                "                System.out.println(\"BeanName:\" + beanName);\n" +
                "                System.out.println(\"Bean的类型：\" + beanType);\n" +
                "                System.out.println(\"Bean所在的包：\" + beanType.getPackage());\n" +
                "                System.out.println(\"Bean：\" + applicationContext.getBean(\n" +
                "                        beanName));\n" +
                "            }*/\n" +
                "            // ===== 在项目初始化bean后检验数据库连接是否\n" +
                "            DataSource dataSource = (DataSource) applicationContext.getBean(\"shardingDataSource\");\n" +
                "            Connection connection = dataSource.getConnection();\n" +
                "            connection.close();\n" +
                "        } catch (Exception e) {\n" +
                "            System.exit(-1);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}\n";
    }
}
