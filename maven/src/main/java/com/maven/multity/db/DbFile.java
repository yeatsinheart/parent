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

        String config=basePackage+File.separator+"config";
        String mybatisPlusConfig=config+File.separator+"MybatisPlusConfig.java";
        FileUtil.write(mybatisPlusConfig,mybatisPlusConfig(project),true);

        String baseResource = base+File.separator+"resources";
        String mapperxml=baseResource+File.separator+"mapper";
        FileUtil.mkdir(mapperxml);
        String resources = baseResource+File.separator+"application.properties";
        FileUtil.write(resources,resource(project),true);
        String datasql = baseResource+File.separator+"datasql.sql";
        FileUtil.write(datasql,datasql(project),true);
    }
    public static String mybatisPlusConfig(String project) {
        return "package "+level+"." + project + ".config;\n" +
                "import org.mybatis.spring.annotation.MapperScan;\n" +
                "import org.springframework.context.annotation.Bean;\n" +
                "import org.springframework.context.annotation.Configuration;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.annotation.DbType;\n" +
                "import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;\n" +
                "import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;\n" +
                "\n" +
                "//mybatis-plus.mapper-locations.classpath*=mapper/*.xml\n" +
                "@Configuration\n" +
                "@MapperScan(\"db.*.mapper\")\n" +
                "public class MybatisPlusConfig {\n" +
                "\n" +
                "    @Bean\n" +
                "    public MybatisPlusInterceptor mybatisPlusInterceptor() {\n" +
                "        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();\n" +
                "        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));\n" +
                "        return interceptor;\n" +
                "    }\n" +
                "\n" +
                "}\n";
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
                "mybatis-plus.mapper-locations.classpath*=mapper/*.xml\n" +
                "# 配置文件指定执行sql（静态资源）\n" +
                "# spring.datasource.initialization-mode=always\n" +
                "# schema.sql中一般存放的是DDL脚本\n" +
                "# spring.datasource.schema = classpath*:schema.sql \n" +
                "# data.sql中一般存放的是DQL（数据查询）脚本或DML（数据操作）脚本\n" +
                "spring.datasource.data =  classpath*:datasql.sql\n" +
                "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver\n" +
                "# 北京时间东八区serverTimezone=GMT%2B8 UTC代表的是全球标准时间 默认使用全球标准时间。" +
                "# 服务器传入的时间会被格式化为serverTimezone 所以使用时间戳存时间最好\n" +
                "spring.datasource.url=jdbc:mysql://数据库IP:3306/数据库名称?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true\n" +
                "spring.datasource.username=数据库访问用户\n" +
                "spring.datasource.password=数据库访问用户密码\n" +
                ""
                ;
    }
}
