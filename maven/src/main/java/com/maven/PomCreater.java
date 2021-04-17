package com.maven;

public class PomCreater {

    public static String webpom(String project){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>"+project+"</artifactId>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>"+project+"-web</artifactId>\n" +
                "<dependencies>\n" +
                "    <dependency>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <artifactId>"+project+"-service</artifactId>\n" +
                "        <version>${project.version}</version>\n" +
                "    </dependency>\n" +
                "    <dependency>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <artifactId>"+project+"-api</artifactId>\n" +
                "        <version>${project.version}</version>\n" +
                "    </dependency>\n" +
                "</dependencies>\n" +
                "</project>";
    }
    public static String dbpom(String project){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>"+project+"</artifactId>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>"+project+"-db</artifactId>\n" +
                "<dependencies>\n" +
                "</dependencies>\n" +
                "</project>";
    }
    public static String apipom(String project){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>"+project+"</artifactId>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>"+project+"-api</artifactId>\n" +
                "<dependencies>\n" +
                "</dependencies>\n" +
                "</project>";
    }
    public static String servicepom(String project){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>"+project+"</artifactId>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <version>1.0-SNAPSHOT</version>\n" +
                "    </parent>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <artifactId>"+project+"-service</artifactId>\n" +
                "<dependencies>\n" +
                "    <dependency>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <artifactId>common</artifactId>\n" +
                "        <version>${project.version}</version>\n" +
                "    </dependency>\n" +
                "    <dependency>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <artifactId>"+project+"-db</artifactId>\n" +
                "        <version>${project.version}</version>\n" +
                "    </dependency>\n" +
                "    <dependency>\n" +
                "        <groupId>com.casino</groupId>\n" +
                "        <artifactId>"+project+"-api</artifactId>\n" +
                "        <version>${project.version}</version>\n" +
                "    </dependency>\n" +
                "</dependencies>\n" +
                "</project>";
    }

    public static String projectpom(String project){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <parent>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <groupId>com.casino</groupId>\n" +
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
