package com.maven.multity.web;

import com.common.utils.FileUtil;

import java.io.File;

public class WebPom {

    public static String level = "web";

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
                "<!--https://hellopz.com/2020/07/27/webflux-%E4%B8%AD%E4%BD%BF%E7%94%A8-swagger/ -->\n" +
                "    <dependencies>\n" +
                "       <dependency>\n" +
                "           <groupId>code." + business + "</groupId>\n" +
                "           <artifactId>" + project + "-service</artifactId>\n" +
                "           <version>${project.version}</version>\n" +
                "       </dependency>\n" +
                "       <dependency>\n" +
                "           <groupId>org.springframework.boot</groupId>\n" +
                "           <artifactId>spring-boot-starter-webflux</artifactId>\n" +
                "       </dependency>\n" +
                "       <dependency>\n" +
                "           <groupId>org.springdoc</groupId>\n" +
                "           <artifactId>springdoc-openapi-webflux-ui</artifactId>\n" +
                "           <version>1.5.7</version>\n" +
                "       </dependency>\n" +
                "       <dependency>\n" +
                "           <groupId>org.springdoc</groupId>\n" +
                "           <artifactId>springdoc-openapi-webflux-core</artifactId>\n" +
                "           <version>1.5.7</version>\n" +
                "       </dependency>\n" +
                "       <dependency>\n" +
                "           <groupId>org.springframework.boot</groupId>\n" +
                "           <artifactId>spring-boot-starter-thymeleaf</artifactId>\n" +
                "       </dependency>\n" +
                "   </dependencies>\n" +
                "   <build>\n" +
                "        <plugins>\n" +
                "            <plugin>\n" +
                "                <groupId>org.springframework.boot</groupId>\n" +
                "                <artifactId>spring-boot-maven-plugin</artifactId>\n" +
                "                <version>2.4.4</version>\n" +
                "                <configuration>\n" +
                "                    <fork>\n" +
                "                        true\n" +
                "                    </fork>\n" +
                "                    <includeSystemScope>true</includeSystemScope>\n" +
                "                </configuration>\n" +
                "                <executions>\n" +
                "                    <execution>\n" +
                "                        <goals>\n" +
                "                            <goal>repackage</goal>\n" +
                "                        </goals>\n" +
                "                    </execution>\n" +
                "                </executions>\n" +
                "            </plugin>\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "</project>";
    }
}
