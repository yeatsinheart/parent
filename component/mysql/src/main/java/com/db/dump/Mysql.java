package com.db.dump;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Mysql {
    /**
     * 备份mysql数据库
     *
     * @param root      mysql登录名
     * @param rootPass  登录密码
     * @param dbName    要备份的数据库名称
     * @param tableName 这里实现了只备份某一张表或多张表
     *                  这里使用了不确定参数
     *                  备份mysql数据库的命令 mysqldump -hhostname -uusername -ppassword
     *                  databasename > backupfile.sql
     * @return
     */
    public static String dbBackUp(String root, String rootPass, String dbName, String... tableName) {
        // 生成备份文件
        String pathSql = dbName + ".sql";
        try {
            File fileSql = new File(pathSql);
            if (!fileSql.exists()) {
                fileSql.createNewFile();
            }
            String mysql = "mysqldump -u" + root + " -p" + rootPass + " --databases  " + dbName + " ";
            for (String string : tableName) {
                mysql = mysql + string + " ";
            }
            mysql = mysql + " --default-character-set=utf8 ";
            System.out.println(mysql);
            System.out.println("cmd命令为：——>>>" + mysql);
            Runtime runtime = Runtime.getRuntime();
            Process child = runtime.exec(mysql);
            // 读取备份数据并生成文件
            InputStream in = child.getInputStream();
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pathSql), StandardCharsets.UTF_8);
            writer.write("DROP DATABASE IF EXISTS `" + dbName + "`;" + "\n");
            writer.write("CREATE DATABASE  `" + dbName + "`;" + "\n");
            writer.write("use  `" + dbName + "`;" + "\n");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line + "\n");
                line = reader.readLine();
            }
            writer.flush();
            System.out.println("数据库已备份到——>>" + pathSql);
        } catch (Exception e) {
            System.out.println("生成m=mysql文件失败");
        }
        return pathSql;
    }

    public static boolean recovery(String root, String rootPass, String dbName) {
        try {
            Runtime rt = Runtime.getRuntime();
            System.out.println("还原");
            // 调用 mysql 的 cmd:
            //+ dbName
            String mysql = "mysql -u" + root + " -p" + rootPass + " ";
            System.out.println(mysql);
            //"cmd /c " +c
            Process child = rt.exec(mysql);
            OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
            String inStr;
            StringBuffer sb = new StringBuffer();
            String outStr;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dbName + ".sql"), StandardCharsets.UTF_8));
            while ((inStr = br.readLine()) != null) {
                System.out.println(inStr);
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();
            // 别忘记关闭输入输出流
            out.close();
            br.close();
            writer.close();
            System.out.println("还原成功");
        } catch (Exception e) {
            System.out.println("还原失败");
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        String user = "root";
        String pwd = "zane";
       // String pwd = "zdc1991";
        String db = "code";
        dbBackUp(user,pwd,db);
        //recovery(user, pwd, db);
    }
}
