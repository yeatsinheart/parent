package com.gen.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

public class Freemarker {

    /**
     * 输出到文件
     */
    public static void printFile(Template template, Map<String, Object> data, String outFileName, String outFilePath) {
        try {
//			String base = PathUtil.getClasspath();
//			if(filePath.startsWith("/")) base = "";
            File outFile = new File(outFilePath + outFileName);
            if (!outFile.getParentFile().exists()) {                //判断有没有父路径，就是判断文件整个路径是否存在
                outFile.getParentFile().mkdirs();                //不存在就全部创建
            }
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8.displayName()));
            template.process(data, out);                    //模版输出
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件名（）获取模板文件
     */
    public static Template getTemplate(String ftlName, String ftlPath) {
        try {
            //通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            cfg.setEncoding(Locale.CHINA, StandardCharsets.UTF_8.displayName());
            //设定去哪里读取相应的ftl模板文件
            cfg.setDirectoryForTemplateLoading(new File(ftlPath));
            //在模板文件目录中找到名称为name的文件
            Template temp = cfg.getTemplate(ftlName);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(String ftlPath, String ftlName, Map<String, Object> data, String outFilePath, String outFileName) {
        Template template = getTemplate(ftlName, ftlPath);
        printFile(template, data, outFileName, outFilePath);
    }


}
