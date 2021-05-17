package com.maven.multity;

import com.base.utils.StringUtil;

import java.util.List;

public class BaseRunner {
    public static String starter(String level,String project) {
        return "package " + level + "." + project + ";\n" +
                "\n" +
                "import com.base.utils.ShutDown;\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "import org.springframework.context.ConfigurableApplicationContext;\n" +
                "import org.springframework.context.annotation.ComponentScan;\n" +
                "import javax.sql.DataSource;\n" +
                "import java.sql.Connection;\n" +

                "\n" +
                "@ComponentScan(basePackages={\n" +
                "        \"" + level + "." + project + ".controller\",\n" +
                "        \"service." + project + ".services\",\n" +
                "        \"com.db.config\",\n" +
                "        \"db." + project + ".daos.impls\",\n" +
                "        \"com.redis\",\n" +
                "        \"com.config.refresh\",\n" +
                "        \"com.base.pool\",\n" +
                "        \"com.base.annotation\"\n" +
                "})\n" +
                "@SpringBootApplication\n" +
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
