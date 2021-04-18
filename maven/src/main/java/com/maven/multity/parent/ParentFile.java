package com.maven.multity.parent;

import com.common.utils.FileUtil;

import java.io.File;

public class ParentFile {

    public static void init(String projectpath,String project){
        //初始化文件夹结构
        String base = projectpath+File.separator+"src"+File.separator+"main";
        String basePackage=base+File.separator+"api"+File.separator+project;

    }
}
