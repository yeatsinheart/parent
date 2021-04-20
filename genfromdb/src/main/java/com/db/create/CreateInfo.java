package com.db.create;

import java.util.HashMap;
import java.util.Map;

public class CreateInfo {
    public String project;
    public String packageName;
    public String objectName;
    public String className;
    public String fullName;
    public String fileName;
    public String filePath;
    public String fileSuffix;
    public String model;
    public String modelFileName;

    public CreateInfo(String project,String model, String packageName, String filePath, String fileSuffix) {
        this.project = project;
        this.model = model;
        this.modelFileName = this.model+".ftl";
        this.packageName = packageName;
        String[] packageNames = packageName.split("\\.");
        this.filePath = filePath + String.join("/", packageNames) +"/";
        this.fileSuffix = fileSuffix;
    }

    public void setTableInfo(String objectName) {
        if(model.equals("dto")){
            this.className = captureName(objectName) + "DTO";
        }else{

            this.className = captureName(objectName) + captureName(model);
        }
        this.fullName = this.packageName + "." + this.className;
        this.fileName = this.className + this.fileSuffix;
        this.objectName = captureName(objectName);
    }

    //首字母大写 如果本身就是大写就有问题了不是
    public static String captureName(String name) {
        char[] ch = name.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
    public Map getMap(){
        Map map = new HashMap();
        map.put("project",this.project);
        map.put("packageName",this.packageName);
        map.put("className",this.className);
        map.put("fullName",this.fullName);
        map.put("fileName",this.fileName);
        map.put("filePath",this.filePath);
        map.put("fileSuffix",this.fileSuffix);
        map.put("model",this.model);
        map.put("objectName",this.objectName);
        return map;
    }

}
