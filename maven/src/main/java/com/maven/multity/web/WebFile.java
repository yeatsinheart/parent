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
        FileUtil.mkdir(controller);


        String baseResource = base+File.separator+"resources";
        FileUtil.mkdir(baseResource);

        String resources = baseResource+File.separator+"application.properties";
        FileUtil.write(resources,resource(project),true);
    }
    public static String resource(String project){
        return "spring.application.name=" +project+"-"+level+"\n"+
                "# 日志颜色\n" +
                "spring.output.ansi.enabled=always\n" +
                "#logging.level.root=debug\n" +
                "logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint}%clr(${LOG_LEVEL_PATTERN:%5p}) %clr(${PID}){magenta}%clr([%t]){faint} %clr(%-40.40logger{39}){cyan}[line:%line]%clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:%wEx}\n" +
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
                "\n" +
                "import javax.sql.DataSource;\n" +
                "import java.sql.Connection;\n" +
                "\n" +
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
