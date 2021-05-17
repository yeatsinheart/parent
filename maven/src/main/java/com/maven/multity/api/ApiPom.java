package com.maven.multity.api;

import com.base.utils.FileUtil;

import java.io.File;

public class ApiPom {
    public static String level = "api";

    public static void init(String business, String projectpath, String project) {
        FileUtil.mkdir(projectpath);
        FileUtil.create(projectpath + File.separator + "pom.xml");
        FileUtil.write(projectpath + File.separator + "pom.xml", pom(business, project), true);

    }

    public static String pom(String business, String project) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>" + project + "</artifactId>\n" +
                "        <groupId>code." + business + "</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>" + project + "-" + level + "</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "    <dependencies>\n" +
                "        <dependency>\n" +
                "           <groupId>code</groupId>\n" +
                "           <artifactId>base</artifactId>\n" +
                "           <version>${project.version}</version>\n" +
                "            <!--提供编译而不参与打包-->\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "</project>";
    }
}
