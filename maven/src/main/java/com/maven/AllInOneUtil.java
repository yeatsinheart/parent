package com.maven;

import com.common.utils.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AllInOneUtil {
    public static void main(String[] args) {
        List<String> modules = Arrays.asList(
                "global","tenant", "user",
                "cost","money","game","chat",
                "bus",
                "report"
        );
        String base = "business";
        System.out.println(dubboScan(modules, base));
        FileUtil.write(base + File.separator + "all" + File.separator + "pom.xml", writetoAll(modules, base), true);
        FileUtil.write(base + File.separator + "pom.xml", writetoBase(modules, base), true);
        FileUtil.write(base + File.separator + "all" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "all" + File.separator + "AllApplication.java", componentScan(modules, base), true);
        FileUtil.write(base + File.separator + "all" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "application.properties", resources(modules, base), true);
    }

    public static String resources(List<String> modules, String base) {
        return "spring.application.name=all\n" +
                "server.port=0\n" +
                "# 日志颜色\n" +
                "spring.output.ansi.enabled=always\n" +
                "#logging.level.root=debug\n" +
                "logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint}%clr(${LOG_LEVEL_PATTERN:%5p}) %clr(${PID}){magenta}%clr([%t]){faint} %clr(%-40.40logger{39}){cyan}[line:%line]%clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:%wEx}\n" +
                "#开启配置预加载功能\n" +
                "nacos.config.bootstrap.enable=true\n" +
                "# 主配置服务器地址\n" +
                "nacos.config.server-addr=192.168.15.100:8848\n" +
                "#nacos.config.context-path=nacos\n" +
                "nacos.config.data-ids=application\n" +
                "nacos.config.namespace=\n" +
                "nacos.config.group=DEFAULT_GROUP\n" +
                "nacos.config.type=properties\n" +
                "nacos.config.max-retry=10\n" +
                "# 主配置 开启自动刷新\n" +
                "nacos.config.auto-refresh=true\n" +
                "# 主配置 重试时间\n" +
                "nacos.config.config-retry-time=2333\n" +
                "# 主配置 配置监听长轮询超时时间\n" +
                "nacos.config.config-long-poll-timeout=46000\n" +
                "# 主配置 开启注册监听器预加载配置服务（除非特殊业务需求，否则不推荐打开该参数）\n" +
                "nacos.config.enable-remote-sync-config=true\n";
    }

    public static String dubboScan(List<String> modules, String base) {
        StringBuffer sb = new StringBuffer();
        for (String m : modules) {
            sb.append("service.");
            sb.append(m);
            sb.append("services.impls");
            sb.append(",");
        }
        return sb.toString();
    }

    public static String componentScan(List<String> modules, String base) {
        StringBuffer sb = new StringBuffer("    \n");
        for (String m : modules) {
            sb.append("        \"service." + m + ".services\",\n");
            sb.append("        \"db." + m + ".daos.impls\",\n");
        }
        sb.append("        \"com.nacos.config\",\n");
        sb.append("        \"com.db.config\",\n");
        sb.append("        \"com.redis\",\n");
        sb.append("        \"com.common.pool\",\n");
        sb.append("        \"com.common.annotation\",\n");
        sb.append("        \"com.common.event\",\n");
        String content = "package com.all;\n" +
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
                sb +
                "})\n" +
                "public class AllApplication {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Runtime.getRuntime().addShutdownHook(new ShutDown());\n" +
                "        ConfigurableApplicationContext applicationContext = SpringApplication.run(AllApplication.class, args);\n" +
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
                "}\n\n";
        return content;
    }

    public static String writetoBase(List<String> modules, String base) {
        String pom = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <groupId>code</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <packaging>pom</packaging>\n" +
                "    <groupId>code.business</groupId>\n" +
                "    <artifactId>business</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "    <modules>\n" +
                modules(modules, base) +
                "        <module>all</module>\n" +
                "    </modules>\n" +
                "</project>\n";
        return pom;
    }

    public static String writetoAll(List<String> modules, String base) {
        String pom = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>" + base + "</artifactId>\n" +
                "        <groupId>code." + base + "</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <artifactId>all</artifactId>\n" +
                dependencies(modules, base) +
                "</project>\n";
        return pom;
    }

    public static String dependencies(List<String> modules, String base) {
        StringBuffer sb = new StringBuffer("    <dependencies>\n");
            String g = "code." + base;
        sb.append(
                "        <dependency>\n" +
                        "            <groupId>code</groupId>\n" +
                        "            <artifactId>base</artifactId>\n" +
                        "            <version>${project.version}</version>\n" +
                        "            <!--提供编译而不参与打包-->\n" +
                        "            <scope>provided</scope>\n" +
                        "        </dependency>\n");
        sb.append(
                "        <dependency>\n" +
                        "            <groupId>code</groupId>\n" +
                        "            <artifactId>dubbo</artifactId>\n" +
                        "            <version>${project.version}</version>\n" +
                        "            <!--提供编译而不参与打包-->\n" +
                        "            <scope>provided</scope>\n" +
                        "        </dependency>\n");
        for (String m : modules) {
            String a = m + "-service";
            sb.append(
                    "        <dependency>\n" +
                            "            <groupId>" + g + "</groupId>\n" +
                            "            <artifactId>" + a + "</artifactId>\n" +
                            "            <version>${project.version}</version>\n" +
                            "        </dependency>\n");
        }
        sb.append("    </dependencies>\n");
        return sb.toString();
    }

    public static String modules(List<String> modules, String base) {
        StringBuffer sb = new StringBuffer("    \n");
        for (String m : modules) {
            sb.append(
                    "        <module>" + m + "</module>\n");
        }
        return sb.toString();
    }

}
