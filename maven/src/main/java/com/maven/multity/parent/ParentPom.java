package com.maven.multity.parent;

import com.common.utils.FileUtil;

import java.io.File;

public class ParentPom {
    public static void init(String parent,String projectpath,String project){
        FileUtil.mkdir(projectpath);
        FileUtil.create(projectpath+ File.separator+"pom.xml");
        FileUtil.write(projectpath+ File.separator+"pom.xml",pom(parent,project),true);
    }
    public static String pom(String business,String project){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>"+business+"</artifactId>\n" +
                "        <groupId>code."+business+"</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>"+project+"</artifactId>\n" +
                "    <packaging>pom</packaging>\n" +
                "    <modules>\n" +
                "        <module>"+project+"-web</module>\n" +
                "        <module>"+project+"-api</module>\n" +
                "        <module>"+project+"-service</module>\n" +
                "        <module>"+project+"-db</module>\n" +
                "    </modules>\n" +
                "</project>";
    }
}
