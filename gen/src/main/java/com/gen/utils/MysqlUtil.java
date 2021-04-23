package com.gen.utils;

import com.gen.entities.Column;
import com.gen.entities.Table;
import com.gen.mapper.DBMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MysqlUtil {
    public static Map<String, String> java2dataBase = new HashMap<>();
    public static Map<String, String> dataBase2java = new HashMap<>();

    static {
        dataBase2java.put("VARCHAR", "java.lang.String");
        dataBase2java.put("CHAR", "java.lang.String");
        dataBase2java.put("BLOB", "java.lang.String");
        dataBase2java.put("TEXT", "java.lang.String");
        dataBase2java.put("INTEGER", "java.lang.Long");
        dataBase2java.put("TINYINT", "java.lang.Integer");
        dataBase2java.put("INT", "java.lang.Integer");
        dataBase2java.put("BOOLEAN", "java.lang.Integer");
        dataBase2java.put("SMALLINT", "java.lang.Short");
        dataBase2java.put("MEDIUMINT", "java.lang.Integer");
        dataBase2java.put("BIT", "java.lang.Boolean");
        dataBase2java.put("FLOAT", "java.lang.Float");
        dataBase2java.put("DOUBLE", "java.lang.Double");
        dataBase2java.put("DECIMAL", "java.math.BigDecimal");
        dataBase2java.put("BIGINT", "java.lang.Long");
        dataBase2java.put("ID", "java.lang.Long");
        dataBase2java.put("DATE", "java.time.LocalDate");
        dataBase2java.put("TIME", "java.time.LocalTime");
        dataBase2java.put("DATETIME", "java.time.LocalDateTime");
        dataBase2java.put("TIMESTAMP", "java.time.LocalDateTime");
        dataBase2java.forEach((k, v) -> {
            java2dataBase.put(v, k);
        });
    }

    public static String getJavaType(String columnType) {
        String type = columnType
                .replaceAll(" without time zone", "")
                .replaceAll(" with time zone", "")
                .split("\\(")[0].toUpperCase();
        return dataBase2java.get(type);
    }


    public static SqlSessionFactory connect(String db, String ipportdatase, String user, String pwd) {
        SqlSessionFactory sqlSessionFactory = null;
        DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://" + ipportdatase + "?useUnicode=true&serverTimezone=UTC&useSSL=false&autoReconnect=true&characterEncoding=utf-8")
                .username(user)
                .password(pwd).build();
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        Configuration configuration = new Configuration();
        configuration.addMappers("com.gen.mapper");
        factoryBean.setConfiguration(configuration);
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/gen/mapper/*Mapper.xml");
        try {
            factoryBean.setMapperLocations(context.getResources("classpath*:com/gen/mapper/*Mapper.xml"));
        } catch (IOException e) {
            log.error("出错", e);
        }
        try {
            sqlSessionFactory = factoryBean.getObject();
        } catch (Exception e) {
            log.error("出错", e);
        }
        return sqlSessionFactory;
    }

    public static String getTableSql(List<String> tables) {
        String sql = "SELECT  " +
                "    table_name name, " +
                "    engine, " +
                "    table_comment comment, " +
                "    create_time createTime " +
                "FROM " +
                "    information_schema.tables " +
                "WHERE " +
                "    table_schema = (SELECT DATABASE()) AND  table_name in (#{tables})  ORDER BY createTime ";
        //所有表
        if (CollectionUtils.isEmpty(tables)) {
            return sql
                    .replaceAll("AND table_name in \\(#\\{tables\\}\\)", "")
                    .replaceAll("AND A.tablename in \\(#\\{tables\\}\\)", "")
                    .replaceAll("and relname in \\(#\\{tables\\}\\)", "");
        }
        //指定表
        return sql.replaceAll("#\\{tables\\}", "'" +
                String.join("','", tables) + "'")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "");
    }

    public static String getColumnSql(String tableName) {
        String sql = "SELECT \n" +
                "A.TABLE_NAME 'table_name', \n" +
                "A.COLUMN_NAME 'name', \n" +
                "A.DATA_TYPE 'raw_type', \n" +
                "A.COLUMN_TYPE 'type',\n" +
                "CASE WHEN A.NUMERIC_PRECISION IS NOT NULL THEN A.NUMERIC_PRECISION  WHEN A.CHARACTER_MAXIMUM_LENGTH IS NOT NULL THEN A.CHARACTER_MAXIMUM_LENGTH ELSE NULL  END 'length',\n" +
                "CASE WHEN A.NUMERIC_SCALE IS NOT NULL THEN A.NUMERIC_SCALE ELSE NULL  END 'scale',\n" +
                "CASE WHEN A.IS_NULLABLE = 'YES' THEN 'false' ELSE 'true'  END 'notNull', A.COLUMN_KEY 'key', A.COLUMN_COMMENT 'comment' \n" +
                "FROM information_schema.COLUMNS A \n" +
                "WHERE TABLE_NAME='#{tableName}' AND TABLE_SCHEMA=(SELECT DATABASE()) \n" +
                "ORDER BY TABLE_NAME ";
        return sql.replaceAll("#\\{tableName}", "" + tableName + "");
    }

    public static void main(String[] args) {
        SqlSessionFactory session = connect("mysql", "47.242.219.77:3306/chzx_chat", "root", "IQdtJcwVuspR0WT6");
        SqlSessionTemplate template = new SqlSessionTemplate(session);
        DBMapper tableMapper = template.getMapper(DBMapper.class);
        String tableSql = getTableSql(Arrays.asList("ad_user"));
        List<Table> tablees = tableMapper.getAllTables(tableSql);
        ExecutorService pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (Table table : tablees) {
            String columnSql = getColumnSql(table.getName());
            List<Column> columns = tableMapper.getAllColumns(columnSql);
            table.setColumnList(columns);
            pool.execute(() -> FileGenUtil.gen(table, "demo", "business"));
        }
    }
}
