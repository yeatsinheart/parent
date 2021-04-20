package com.maven.multity.db;

import com.common.utils.FileUtil;
import com.common.utils.StringUtil;

import java.io.File;

public class DbFile {

    public static String level="db";
    public static void init(String projectpath,String project){
        
        //初始化文件夹结构
        String src = projectpath+File.separator+"src";
        FileUtil.mkdir(src+File.separator+"test"+File.separator+"java"+File.separator+level+File.separator+project);
        String base = src+ File.separator+"main";

        String basePackage=base+File.separator+"java"+File.separator+level+File.separator+project;
        String starter=basePackage+File.separator+ StringUtil.firstUpper(project)+StringUtil.firstUpper(level)+"Application.java";
        FileUtil.write(starter,starter(project),true);

        String entities=basePackage+File.separator+"entities";
        FileUtil.mkdir(entities);
        String mapper=basePackage+File.separator+"mapper";
        FileUtil.mkdir(mapper);

        String baseResource = base+File.separator+"resources";
        String mapperxml=baseResource+File.separator+"mapper";
        FileUtil.mkdir(mapperxml);
        String resources = baseResource+File.separator+"application.properties";
        FileUtil.write(resources,resource(project),true);
        String datasql = baseResource+File.separator+"datasql.sql";
        FileUtil.write(datasql,datasql(project),true);
    }

    public static String starter(String project){
        return "package "+level+"."+project+";\n" +
                "\n" +
                "import com.common.utils.ShutDown;\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "import org.springframework.context.annotation.ComponentScan;\n" +
                "import org.springframework.context.ConfigurableApplicationContext;\n" +
                "\n" +
                "import javax.sql.DataSource;\n" +
                "import java.sql.Connection;\n" +
                "\n" +
                "@SpringBootApplication\n" +
                "@ComponentScan(basePackages = {\n" +
                "        \"com.db.config\",\n" +
                "        \"com.common.annotation\"\n" +
                "})\n" +
                "public class "+ StringUtil.firstUpper(project) +StringUtil.firstUpper(level) +"Application {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        Runtime.getRuntime().addShutdownHook(new ShutDown());\n" +
                "        ConfigurableApplicationContext applicationContext = SpringApplication.run("+StringUtil.firstUpper(project) +StringUtil.firstUpper(level)+"Application.class, args);\n" +
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
    public static String datasql(String project){
        return "SELECT 1 FROM DUAL;";
    }
    public static String resource(String project){
        return "spring.application.name=" +project+"-"+level+"\n"+
                "spring.output.ansi.enabled=always\n" +
                "#logging.level.root=debug\n" +
                "logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint}%clr(${LOG_LEVEL_PATTERN:%5p}) %clr(${PID}){magenta}%clr([%t]){faint} %clr(%-40.40logger{39}){cyan}[line:%line]%clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:%wEx}\n" +
                ""
                ;
    }
}
