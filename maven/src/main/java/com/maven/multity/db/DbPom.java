package com.maven.multity.db;

import com.common.utils.FileUtil;

import java.io.File;

public class DbPom {

    public static String level="db";
    public static void init(String parentPackage,String projectpath,String project){
        FileUtil.mkdir(projectpath);
        FileUtil.create(projectpath+ File.separator+"pom.xml");
        FileUtil.write(projectpath+ File.separator+"pom.xml",pom(parentPackage,project),true);
    }
    public static String pom(String parentPackage,String project){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>"+project+"</artifactId>\n" +
                "        <groupId>"+parentPackage+"</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>"+project+"-"+level+"</artifactId>\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "            <groupId>"+parentPackage+"</groupId>\n" +
                "            <artifactId>common</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>mysql</groupId>\n" +
                "            <artifactId>mysql-connector-java</artifactId>\n" +
                "            <version>8.0.23</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.mybatis.spring.boot</groupId>\n" +
                "            <artifactId>mybatis-spring-boot-starter</artifactId>\n" +
                "            <version>2.1.4</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>com.baomidou</groupId>\n" +
                "            <artifactId>mybatis-plus</artifactId>\n" +
                "            <version>3.4.2</version>\n" +
                "            <scope>compile</scope>\n" +
                "        </dependency>\n" +
                "        <!--<dependency>\n" +
                "            <groupId>org.apache.shardingsphere</groupId>\n" +
                "            <artifactId>sharding-jdbc-spring-boot-starter</artifactId>\n" +
                "            <version>4.1.1</version>\n" +
                "        </dependency>-->\n"+
                "    </dependencies>\n" +
                "</project>";
    }
}
