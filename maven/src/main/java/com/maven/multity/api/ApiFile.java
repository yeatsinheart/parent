package com.maven.multity.api;

import com.common.utils.FileUtil;

import java.io.File;

public class ApiFile {

    public static String level="api";
    public static void init(String projectpath,String project){
        //初始化文件夹结构
        String base = projectpath+File.separator+"src"+File.separator+"main";

        String basePackage=base+File.separator+"java"+File.separator+level+File.separator+project;
        String dtos=basePackage+File.separator+"dtos";
        String constants=basePackage+File.separator+"constants";
        String services=basePackage+File.separator+"services";
        FileUtil.mkdir(dtos);
        FileUtil.mkdir(constants);
        FileUtil.mkdir(services);

    }
}
