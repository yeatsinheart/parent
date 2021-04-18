package com.maven.multity.web;

import com.common.utils.FileUtil;

import java.io.File;

public class WebPom {

    public static String level="web";
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
                "<!--https://hellopz.com/2020/07/27/webflux-%E4%B8%AD%E4%BD%BF%E7%94%A8-swagger/ -->\n" +
                "    <dependencies>\n" +
                "       <dependency>\n" +
                "           <groupId>"+parentPackage+"</groupId>\n" +
                "           <artifactId>"+project+"-service</artifactId>\n" +
                "           <version>${project.version}</version>\n" +
                "       </dependency>\n" +
                "       <dependency>\n" +
                "           <groupId>org.springframework.boot</groupId>\n" +
                "           <artifactId>spring-boot-starter-webflux</artifactId>\n" +
                "       </dependency>\n"+
                "       <dependency>\n" +
                "           <groupId>org.springdoc</groupId>\n" +
                "           <artifactId>springdoc-openapi-webflux-ui</artifactId>\n" +
                "           <version>1.5.7</version>\n" +
                "       </dependency>\n" +
                "       <dependency>\n" +
                "           <groupId>org.springdoc</groupId>\n" +
                "           <artifactId>springdoc-openapi-webflux-core</artifactId>\n" +
                "           <version>1.5.7</version>\n" +
                "       </dependency>\n"+
                "   </dependencies>\n" +
                "</project>";
    }
}
