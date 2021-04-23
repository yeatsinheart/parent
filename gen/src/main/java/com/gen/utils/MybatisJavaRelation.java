package com.gen.utils;

import java.util.HashMap;
import java.util.Map;

public class MybatisJavaRelation {
    /**
     * 获取java与mybatis jdbc的映射关系
     */
    public static Map java2jdbc = new HashMap();
    public static Map jdbc2java = new HashMap();

    static {
        jdbc2java.put("CHAR", "java.lang.String");
        jdbc2java.put("LONGVARCHAR", "java.lang.String");
        jdbc2java.put("VARCHAR", "java.lang.String");
        jdbc2java.put("DECIMAL", "java.math.BigDecimal");
        jdbc2java.put("NUMERIC", "java.math.BigDecimal");
        jdbc2java.put("BIT", "java.lang.Boolean");
        jdbc2java.put("BOOLEAN", "java.lang.Boolean");
        jdbc2java.put("TINYINT", "java.lang.Byte");
        jdbc2java.put("SMALLINT", "java.lang.Short");
        jdbc2java.put("INTEGER", "java.lang.Integer");
        jdbc2java.put("BIGINT", "java.lang.Long");
        jdbc2java.put("REAL", "java.lang.Float");
        jdbc2java.put("FLOAT", "java.lang.Float");
        jdbc2java.put("DOUBLE", "java.lang.Double");
        jdbc2java.put("BINARY", "java.lang.Byte[]");
        jdbc2java.put("VARBINARY", "java.lang.Byte[]");
        jdbc2java.put("LONGVARBINARY", "java.lang.Byte[]");
        jdbc2java.put("String", "java.lang.Object");

        /*jdbc2java.put("DATE","java.sql.Date");
        jdbc2java.put("TIME","java.sql.Time");
        jdbc2java.put("TIMESTAMP","java.sql.Timestamp");

        jdbc2java.put("DATE","java.util.Date");
        jdbc2java.put("TIME","java.util.Time");
        jdbc2java.put("TIMESTAMP","java.util.DateTime");*/

        jdbc2java.put("DATE", "java.time.LocalDate");
        jdbc2java.put("TIME", "java.time.LocalTime");
        jdbc2java.put("TIMESTAMP", "java.time.LocalDateTime");

        jdbc2java.put("CLOB", "Clob");
        jdbc2java.put("BLOB", "Blob");
        jdbc2java.put("ARRAY", "Array");
        jdbc2java.put("DISTINCT", "mapping of underlying type");
        jdbc2java.put("STRUCT", "Struct");
        jdbc2java.put("REF", "Ref");
        jdbc2java.put("DATALINK", "java.net.URL");
        jdbc2java.forEach((k, v) -> {
            java2jdbc.put(v, k);
        });
    }
}
