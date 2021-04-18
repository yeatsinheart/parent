package com.db.create;

import com.db.config.ConnectInfo;
import com.db.config.db.Column;
import com.db.config.db.DBService;
import com.db.config.db.Table;
import com.db.create.freemarker.Freemarker;
import com.db.create.handle.toObject.TableHandle;

import java.time.LocalDate;
import java.util.*;

public class WriteObject {
    static List<CreateInfo> modelList = new ArrayList();

    static String PROJECT ="gamming";
    static String SOURCE_FILE = "src/main/";
    static String RESOURCE_FILE = SOURCE_FILE + "/resources/";
    static String MAPPER_FILE = RESOURCE_FILE + "/mapper/";
    static String JAVA_FILE = SOURCE_FILE + "/java/";
    static String MODEL = "com/auto/database/create/freemarker/selfframe/";
    static String MODEL_FILE = System.getProperty("user.dir")+"/database/"+JAVA_FILE + MODEL;
    static String JAVA_FILE_SUFFIX = ".java";
    static String NO_FILE_SUFFIX = "";
    static String PACKAGE_TEST = "com."+PROJECT;
    static String TABLE_PREFIX = "test_";
    static String COLUMN_PREFIX = "";
    static String NO_PACKAGE = "";
    static String TABLE_SUFFIX = "";
    static String COLUMN_SUFFIX = "";
    static String NAME_PREFIX = "I";
    static boolean OPEN_SWAGGER = false;
    static boolean OPEN_LOMBOK = true;

    // 是否本地缓存
    static boolean LOCALCACHEABLE=true;
    public static String getPath(String project,String level){
        return System.getProperty("user.dir")+"/"+project+"/"+project+"-"+level+"/";
    }

    static {
        modelList.add(new CreateInfo(PROJECT,"entity",   "db."+PROJECT+".entity", getPath(PROJECT,"db")+JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT,"dto", "api."+PROJECT + ".dto", getPath(PROJECT,"api")+JAVA_FILE, JAVA_FILE_SUFFIX));
        //modelList.add(new CreateInfo("root", PACKAGE_TEST + ".root", JAVA_FILE, JAVA_FILE_SUFFIX));
       // modelList.add(new CreateInfo("create", PACKAGE_TEST + ".create", JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT,"mapper", "db."+PROJECT + ".mapper", getPath(PROJECT,"db")+JAVA_FILE, JAVA_FILE_SUFFIX));
        //modelList.add(new CreateInfo("controller", PACKAGE_TEST + ".controller", JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT,"service", "api."+PROJECT + ".services", getPath(PROJECT,"api")+JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT,"serviceImpl", "service."+PROJECT + ".services.impls", getPath(PROJECT,"service")+JAVA_FILE, JAVA_FILE_SUFFIX));
        modelList.add(new CreateInfo(PROJECT,"mapper.xml", NO_PACKAGE, getPath(PROJECT,"db")+MAPPER_FILE, NO_FILE_SUFFIX));
    }

    public static void main(String[] args) {
        //ConnectInfo db = ConnectInfo.ONLINE_MYSQL;
        ConnectInfo db = ConnectInfo.ONLINE_MYSQL;
        DBService dbService = new DBService();
        List<String> tableList = Arrays.asList(
                //"gamming_supplier","gamming_self_manage","gamming_kk_platform"
               /* "gamming_supplier_app", "gamming_supplier","gamming_self_manage","gamming_kk_platform",*/
                //"gamming_kk_platform"
                //"gamming_user"
                /*"gamming_channel","gamming_tenant_channel","gamming_tenant_game","gamming_tenant_game_kk","gamming_tenant_game_a5",
                "gamming_type","gamming_type_info"*/
                /*"gamming_user_account"*/
                /*"gamming_channel_info",
                "gamming_channel",
*/
                "gamming_channel",
                "gamming_tenant_channel"
                /*,
                "gamming_channel_game_type",
                "gamming_tenant_game",
                "gamming_game",
                "gamming_channel_game_type_info",
                "gamming_tenant_channel"
                */
        );

        //指定表名
        List<Table> tables = dbService.getAllTables(db, tableList);
        for (Table table : tables) {

            List<Column> columns = dbService.getAllColumns(db, table.getName());
            table.setColumnList(columns);
            /**组装表格数据*/
            Map tableMap = TableHandle.toMap(db.getDriver(), table,
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

    }
}
