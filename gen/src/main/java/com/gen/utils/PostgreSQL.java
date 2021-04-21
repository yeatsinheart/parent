package com.gen.utils;

import java.util.HashMap;
import java.util.Map;

public class PostgreSQL implements SQL {
    public static Map<String, String> java2dataBase = new HashMap();
    public static Map<String, String> dataBase2java = new HashMap();

    static {
        /**
         * 相等  或者 类型根据(拆分成数组
         * */
        //有符号 8 字节整数
        dataBase2java.put("bigint", "java.lang.Long");
        //四字节长有符号整数
        dataBase2java.put("integer", "java.lang.Integer");
        //双精度浮点数字
        dataBase2java.put("double precision", "java.lang.Double");
        //日历日期（年，月，日）
        dataBase2java.put("date", "java.time.LocalDate");
        //定长字符串
        dataBase2java.put("character", "java.lang.String");
        //变长字符串
        dataBase2java.put("character varying", "java.lang.String");
        //逻辑布尔量 （真/假）
        dataBase2java.put("boolean", "java.lang.Boolean");
        //变长位串
        dataBase2java.put("bit varying", "java.lang.String");
        //定长位串
        dataBase2java.put("bit", "java.lang.String");
        //josn字符串
        dataBase2java.put("jsonb", "java.lang.String");
        //日期和时间
        dataBase2java.put("timestamp", "java.time.LocalDateTime");
        //一天里的时间
        dataBase2java.put("time", "java.time.LocalTime");
        //有符号两字节整数
        dataBase2java.put("smallint", "java.lang.Short");
        //可选精度的准确数字
        dataBase2java.put("numeric", "java.math.BigDecimal");
        //变长字符串
        dataBase2java.put("text", "java.lang.String");
        //单精度浮点数
        dataBase2java.put("real", "");
        //自增四字节整数
        dataBase2java.put("serial", "");
        //自增八字节整数
        dataBase2java.put("bigserial", "");
        //时间间隔
        dataBase2java.put("interval", "");
        //二进制数据（"字节数组"）
        dataBase2java.put("bytea", "");
        //平面中的封闭几何路径
        dataBase2java.put("polygon", "");
        //平面中的点
        dataBase2java.put("point", "");
        //平面中的几何路径
        dataBase2java.put("path", "");
        //平面中的圆
        dataBase2java.put("circle", "");
        //平面中的长方形
        dataBase2java.put("box", "");
        //MAC 地址
        dataBase2java.put("macaddr", "");
        //平面中的线段
        dataBase2java.put("lseg", "");
        //平面中的无限长直线
        dataBase2java.put("line", "");
        //IPv4 或者 IPv6网络地址
        dataBase2java.put("inet", "java.lang.String");
        //IPv4 或者 IPv6网络地址
        dataBase2java.put("cidr", "java.lang.String");

        dataBase2java.forEach((k, v) -> java2dataBase.put(v, k));
    }

    public String getJavaType(String columnType) {
        String type = columnType
                .replaceAll(" without time zone", "")
                .replaceAll(" with time zone", "")
                .split("\\(")[0];
        return dataBase2java.get(type);
    }
}
