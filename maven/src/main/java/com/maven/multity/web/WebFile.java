package com.maven.multity.web;

import com.common.utils.FileUtil;
import com.common.utils.StringUtil;

import java.io.File;

public class WebFile {

    public static String level="web";
    public static void init(String projectpath,String project){
        //初始化文件夹结构
        String src = projectpath+File.separator+"src";
        FileUtil.mkdir(src+File.separator+"test"+File.separator+"java"+File.separator+level+File.separator+project);
        String base = src+ File.separator+"main";

        String basePackage=base+File.separator+"java"+File.separator+level+File.separator+project;
        String starter=basePackage+File.separator+ StringUtil.firstUpper(project)+StringUtil.firstUpper(level)+"WebApplication.java";
        FileUtil.write(starter,starter(project),true);

        String pto=basePackage+File.separator+"pto";
        FileUtil.mkdir(pto);

        String controller=basePackage+File.separator+"controller";
        String testController = controller+File.separator+"TestController.java";
        FileUtil.write(testController,testController(project),true);


        String baseResource = base+File.separator+"resources";
        FileUtil.mkdir(baseResource);

        String resources = baseResource+File.separator+"application.properties";
        FileUtil.write(resources,resource(project),true);
    }
    public static String testController(String project){
        return "package web."+project+".controller;\n" +
                "\n" +
                "import api."+project+".services.TestService;\n" +
                "import org.apache.dubbo.config.annotation.DubboReference;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "\n" +
                "@RestController\n" +
                "public class TestController {\n" +
                "    @SuppressWarnings(\"SpringJavaInjectionPointsAutowiringInspection\")\n" +
                "    @DubboReference\n" +
                "    TestService testService;\n" +
                "\n" +
                "    @GetMapping(\"/test\")\n" +
                "    public String test(String i) {\n" +
                "        return testService.test(i);\n" +
                "    }\n" +
                "}\n";
    }
    public static String resource(String project){
        return "spring.application.name=" +project+"-"+level+"\n"+
                "# 日志颜色\n" +
                "spring.output.ansi.enabled=always\n" +
                "#logging.level.root=debug\n" +
                "logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint}%clr(${LOG_LEVEL_PATTERN:%5p}) %clr(${PID}){magenta}%clr([%t]){faint} %clr(%-40.40logger{39}){cyan}[line:%line]%clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:%wEx}\n" +
                "server.port=0\n" +
                "#开启配置预加载功能\n" +
                "nacos.config.bootstrap.enable=true\n" +
                "# 主配置服务器地址\n" +
                "nacos.config.server-addr=192.168.31.140:8848\n" +
                "#nacos.config.context-path=nacos\n" +
                "nacos.config.data-ids=test,database,dubbo\n" +
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
                "nacos.config.enable-remote-sync-config=true\n" +
                ""
                ;
    }
    public static String starter(String project){
        return "package "+level+"."+project+";\n" +
                "\n" +
                "import com.common.utils.ShutDown;\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "import org.springframework.context.ConfigurableApplicationContext;\n" +
                "import org.springframework.context.annotation.ComponentScan;\n" +
                "import javax.sql.DataSource;\n" +
                "import java.sql.Connection;\n" +

                "\n" +
                "@ComponentScan(basePackages={\n" +
                "        \""+level+"."+project+".controller\",\n" +
                "        \"service."+project+".services\",\n" +
                "        \"com.db.config\",\n" +
                "        \"com.common.annotation\"\n" +
                "})\n" +
                "@SpringBootApplication\n" +
                "public class "+ StringUtil.firstUpper(project)+StringUtil.firstUpper(level)+"WebApplication {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Runtime.getRuntime().addShutdownHook(new ShutDown());\n" +
                "        ConfigurableApplicationContext applicationContext = SpringApplication.run("+ StringUtil.firstUpper(project)+StringUtil.firstUpper(level)+"WebApplication.class, args);\n" +
                "        try {\n" +
                "            // ===== 在项目初始化bean后检验数据库连接是否\n" +
                "            DataSource dataSource = (DataSource) applicationContext.getBean(\"dataSource\");\n" +
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
