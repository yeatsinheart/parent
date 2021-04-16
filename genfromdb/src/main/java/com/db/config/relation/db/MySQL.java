package com.db.config.relation.db;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class MySQL  implements SQL {
    public static Map<String, String> java2dataBase = new HashMap();
    public static Map<String, String> dataBase2java = new HashMap();

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

    @Override
    public String getJavaType(String columnType) {
        String type = columnType
                .replaceAll(" without time zone", "")
                .replaceAll(" with time zone", "")
                .split("\\(")[0].toUpperCase();
        return dataBase2java.get(type);
    }
}
