package com.maven;

import com.common.utils.FileUtil;

import java.io.File;

public class Project {

    public static void main(String[] args) {
        multyModule("dubbotest");
    }

    public static void multyModule(String project) {
        FileUtil.remove(project);

        FileUtil.write(project + File.separator + "pom.xml", PomCreater.projectpom(project), true);


        FileUtil.write(project + File.separator + project + "-web" + File.separator +
                "pom.xml", PomCreater.webpom(project), true);
        FileUtil.write(project + File.separator + project + "-web" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "resources"+ File.separator+"application.properties", PropertiesCreater.properties(project+ "-web"), true);
        FileUtil.write(project + File.separator + project + "-web" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "java"+ File.separator+"com"+ File.separator+project+File.separator+"Application.java", StarterCreater.starter(project), true);
        FileUtil.write(project + File.separator + project + "-web" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "java"+ File.separator+"com"+ File.separator+project+File.separator+"controller"+File.separator+"TestController.java", ControllerCreater.starter(project), true);



        FileUtil.write(project + File.separator + project + "-api" + File.separator +
                "pom.xml", PomCreater.apipom(project), true);
        FileUtil.write(project + File.separator + project + "-api" + File.separator +
                "src"+ File.separator+"main"+ File.separator+"resources"+ File.separator+"application.properties", PropertiesCreater.properties(project+ "-api"), true);
        FileUtil.write(project + File.separator + project + "-api" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "java"+ File.separator+"com"+ File.separator+project+File.separator+"Application.java", StarterCreater.starter(project), true);

        FileUtil.write(project + File.separator + project + "-service" + File.separator +
                "pom.xml", PomCreater.servicepom(project), true);
        FileUtil.write(project + File.separator + project + "-service" + File.separator +
                "src"+ File.separator+"main"+ File.separator+"resources"+ File.separator+"application.properties", PropertiesCreater.properties(project+ "-service"), true);
        FileUtil.write(project + File.separator + project + "-service" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "java"+ File.separator+"com"+ File.separator+project+File.separator+"Application.java", StarterCreater.starter(project), true);

        FileUtil.write(project + File.separator + project + "-db" + File.separator +
                "pom.xml", PomCreater.dbpom(project), true);
        FileUtil.write(project + File.separator + project + "-db" + File.separator +
                "src"+ File.separator+"main"+ File.separator+"resources"+ File.separator+"application.properties", PropertiesCreater.properties(project+ "-db"), true);
        FileUtil.write(project + File.separator + project + "-db" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "java"+ File.separator+"com"+ File.separator+project+File.separator+"Application.java", StarterCreater.starter(project), true);
        FileUtil.write(project + File.separator + project + "-db" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "java"+ File.separator+"com"+ File.separator+project+File.separator+"config"+File.separator+"MybatisPlusConfig.java", MybatisCreater.starter(project), true);
        FileUtil.write(project + File.separator + project + "-db" + File.separator +
                "src"+ File.separator+"main"+ File.separator+
                "java"+ File.separator+"com"+ File.separator+project+File.separator+"mapper"+File.separator+"mapper.txt", "", true);

    }

}
