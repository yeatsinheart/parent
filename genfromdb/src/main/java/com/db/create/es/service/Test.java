package com.db.create.es.service;

import com.db.create.freemarker.Freemarker;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
    public static int num = 0;
    public static String espath = "F:\\code\\report\\application\\library\\App\\Report";
    public static Set set = new HashSet();

    public static void main(String[] args) {
        //要遍历的路径
        File file = new File(espath);        //获取其file对象
        func(file);
        set.forEach(s -> System.out.println(s));
    }

    public static String toUpperFristChar(String string) {
        string = string.substring(0, 1).toUpperCase() + string.substring(1);
        return string;
    }

    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine();
        boolean canWrite = false;
        while (line != null) {
            if (line.contains("class") && line.contains("extends")) {

            } else if (line.contains("function")) {
                if(canWrite){
                    buffer.append("*/\r\n"); // 添加换行符
                }
                canWrite = true;
                buffer.append(line.replaceAll("static function", " public void ")
                        .replaceAll("function", " public void ")
                ); // 将读到的内容添加到 buffer 中d
                buffer.append("{/*\r\n"); // 添加换行符
            }else if (line.contains("/**")) {

            } else if (line.contains("*/")) {

            } else {
                if (canWrite) {
                    buffer.append(line.replaceAll("static function", " public void ")
                            .replaceAll("function", " public void ")
                    ); // 将读到的内容添加到 buffer 中d
                    buffer.append("{\r\n"); // 添加换行符
                }
            }


            line = reader.readLine(); // 读取下一行
            if(line==null){
                buffer.append("*/\r\n"); // 添加换行符
            }
        }
        reader.close();
        is.close();
    }

    private static void func(File file) {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory())
                func(f);
            if (f.isFile()) {
                //若是文件，直接打印
                if (!f.getAbsolutePath().split("\\.")[1].equals("php")) continue;
                num++;
                String path = f.getAbsolutePath();
                path = path.replaceAll("F:\\\\code\\\\report\\\\application\\\\library\\\\App\\\\Report", "");
                String[] info = path.split("\\\\");
                String last = info[info.length - 1];
                String fileName = last.split("\\.")[0];
                info[info.length - 1] = toUpperFristChar(fileName) + ".java";
                String packageInfo = "";
                String packagePath = "";
                String raw = "";
                for (int i = 0; i < info.length - 1; i++) {
                    String part = info[i];
                    packageInfo += part + ".";
                    packagePath += part + "\\";
                    raw += part + "/";
                }
                Map<String, Object> data = new HashMap<>();
                data.put("pac", packageInfo.subSequence(0, packageInfo.length() - 1));
                data.put("class", toUpperFristChar(fileName) + "Service");
                data.put("raw", raw + fileName);
                StringBuffer sb = new StringBuffer();
                StringBuffer content = new StringBuffer();
                try {
                    readToBuffer(sb, f.getAbsolutePath());
                    //从第一层开始解析
                    data.put("content", sb);
                    data.put("java", content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Freemarker.write("src/main/java/com/auto/database/create/freemarker/model/", "esservice.ftl", data, "F:\\code\\es-spring-demo\\src\\main\\java\\com\\example\\esspringdemo\\service\\game\\service\\impl" + packagePath, toUpperFristChar(fileName) + "Service.java");
            }

        }

    }
}
