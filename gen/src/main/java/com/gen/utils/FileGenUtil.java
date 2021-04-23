package com.gen.utils;

import com.gen.entities.Column;
import com.gen.entities.Table;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FileGenUtil {
    static String SOURCE_FILE = "src/main/";
    static String RESOURCE_FILE = SOURCE_FILE + "/resources/";
    static String MAPPER_FILE = RESOURCE_FILE + "/mapper/";
    static String JAVA_FILE = SOURCE_FILE + "/java/";
    static String MODEL = "freemarker/selfframe/";
    static String MODEL_FILE = System.getProperty("user.dir") + "/gen/" + RESOURCE_FILE + MODEL;
    static String JAVA_FILE_SUFFIX = ".java";
    static String NO_FILE_SUFFIX = "";
    static String TABLE_PREFIX = "test_";
    static String COLUMN_PREFIX = "";
    static String NO_PACKAGE = "";
    static String TABLE_SUFFIX = "";
    static String COLUMN_SUFFIX = "";
    static String NAME_PREFIX = "I";

    static boolean OPEN_SWAGGER = false;
    static boolean OPEN_LOMBOK = true;
    static boolean LOCALCACHEABLE = true;

    public static String getPath(String project, String level, String requestId) {
        //System.getProperty("user.dir") + File.separator +
        return requestId + File.separator +
                project + File.separator +
                project + "-" + level + File.separator;
    }

    public static void gen(Table table, String PROJECT, String requestId) {

        List<CreateInfo> modelList = new ArrayList();
        modelList.add(new CreateInfo(PROJECT, "entity", "db." + PROJECT + ".entities", getPath(PROJECT, "db", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "mapper.xml", NO_PACKAGE, getPath(PROJECT, "db", requestId) + MAPPER_FILE, NO_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "mapper", "db." + PROJECT + ".mappers", getPath(PROJECT, "db", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "daoImpl", "db." + PROJECT + ".daos.impls", getPath(PROJECT, "db", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "dao", "db." + PROJECT + ".daos", getPath(PROJECT, "db", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "dto", "api." + PROJECT + ".dtos", getPath(PROJECT, "api", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "service", "api." + PROJECT + ".services", getPath(PROJECT, "api", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "serviceImpl", "service." + PROJECT + ".services.impls", getPath(PROJECT, "service", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "pto", "web." + PROJECT + ".ptos", getPath(PROJECT, "web", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT, "controller", "web." + PROJECT + ".controller", getPath(PROJECT, "web", requestId) + JAVA_FILE, JAVA_FILE_SUFFIX));
        //modelList.add(new CreateInfo("root", PACKAGE_TEST + ".root", JAVA_FILE, JAVA_FILE_SUFFIX));
        // modelList.add(new CreateInfo("create", PACKAGE_TEST + ".create", JAVA_FILE, JAVA_FILE_SUFFIX));
        //modelList.add(new CreateInfo("controller", PACKAGE_TEST + ".controller", JAVA_FILE, JAVA_FILE_SUFFIX));

        /**组装表格数据*/
        Map tableMap = toTableMap(table,
                TABLE_PREFIX, TABLE_SUFFIX,
                COLUMN_PREFIX, COLUMN_SUFFIX);
        /**模板填充字段封装*/
        Map writeMap = new HashMap();
        writeMap.put("table", tableMap);
        writeMap.put("author", "Zane");
        writeMap.put("time", LocalDate.now());
        writeMap.put("swagger", OPEN_SWAGGER);
        writeMap.put("lombok", OPEN_LOMBOK);
        writeMap.put("cacheable", LOCALCACHEABLE);

        /**填充模板名称*/
        modelList.forEach(model -> {
            /**模板填充表JAVA名*/
            model.setTableInfo((String) tableMap.get("objectName"));
            writeMap.put(model.model, model.getMap());
        });
        /**生成文件*/
        modelList.forEach(model ->
                Freemarker.write(MODEL_FILE, model.modelFileName, writeMap, model.filePath, model.fileName)
        );
        writeMap.clear();
    }

    public static Map toTableMap(Table table, String tablePrefix, String tableSuffix, String columnPrefix, String columnSuffix) {
        Map<String, Object> map = new HashMap();
        map.put("name", table.getName());
        String fName = table.getName();
        if (table.getName().startsWith(tablePrefix)) {
            fName = fName.replaceAll(tablePrefix, "");
        }
        if (table.getName().endsWith(tableSuffix)) {
            fName = fName.replaceAll(tableSuffix, "");
        }
        map.put("nameFormatted", fName);
        map.put("objectName", NameConvertor.underlineToCamel((String) map.get("nameFormatted")));
        String c = StringUtils.isBlank(table.getComment()) ? "" : table.getComment();
        map.put("comment", c.replaceAll("\\\r", ";").replaceAll("\\\n", ";"));
        List<Map> columns = new ArrayList<>();
        List<Column> columnList = table.getColumnList();
        for (Column column : columnList) {
            columns.add(toCollumnMap(column, columnPrefix, columnSuffix));
        }
        map.put("columns", columns);
        return map;
    }

    public static Map toCollumnMap(Column column, String prefix, String suffix) {
        Map map = new HashMap();
        map.put("name", column.getName());
        map.put("type", column.getType());
        map.put("raw_type", column.getRaw_type());
        map.put("notNull", column.getNotNull());
        map.put("length", column.getLength());
        map.put("scale", column.getScale());
        map.put("key", column.getKey());
        String c = StringUtils.isBlank(column.getComment()) ? "" : column.getComment();
        map.put("comment", c.replaceAll("\\\r", ";").replaceAll("\\\n", ";"));
        String className = MysqlUtil.getJavaType(column.getType());
        //log.error(column.getType() + "=========" + className);
        String[] classNameArray = className.split("\\.");
        map.put("javaType", className);
        map.put("javaTypeName", classNameArray[classNameArray.length - 1]);
        map.put("jdbcType", MybatisJavaRelation.java2jdbc.get(map.get("javaType")));

        map.put("nameFormatted", column.getName().replaceAll(prefix, "").replaceAll(suffix, ""));
        map.put("objectName", NameConvertor.underlineToCamel((String) map.get("nameFormatted")));
        return map;
    }

    public static class CreateInfo {
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

        public CreateInfo(String project, String model, String packageName, String filePath, String fileSuffix) {
            this.project = project;
            this.model = model;
            this.modelFileName = this.model + ".ftl";
            this.packageName = packageName;
            String[] packageNames = packageName.split("\\.");
            this.filePath = filePath + String.join("/", packageNames) + "/";
            this.fileSuffix = fileSuffix;
        }

        //首字母大写 如果本身就是大写就有问题了不是
        public static String captureName(String name) {
            char[] ch = name.toCharArray();
            if (ch[0] >= 'a' && ch[0] <= 'z') {
                ch[0] = (char) (ch[0] - 32);
            }
            return new String(ch);
        }

        public void setTableInfo(String objectName) {
            if (model.equals("dto")) {
                this.className = captureName(objectName) + "DTO";
            } else if (model.equals("pto")) {
                this.className = captureName(objectName) + "PTO";
            } else {
                this.className = captureName(objectName) + captureName(model);
            }
            this.fullName = this.packageName + "." + this.className;
            this.fileName = this.className + this.fileSuffix;
            //log.info(model+":"+objectName+":"+className+":"+fileName);
            this.objectName = captureName(objectName);
        }

        public Map getMap() {
            Map map = new HashMap();
            map.put("project", this.project);
            map.put("packageName", this.packageName);
            map.put("className", this.className);
            map.put("fullName", this.fullName);
            map.put("fileName", this.fileName);
            map.put("filePath", this.filePath);
            map.put("fileSuffix", this.fileSuffix);
            map.put("model", this.model);
            map.put("objectName", this.objectName);
            return map;
        }

    }
}
