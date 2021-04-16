package com.db.config.db;

/**
 * @program: freework
 * @description: this is a 数据库信息
 * @author: Yeats
 * @create: 2019-06-03 14:20
 **/
public enum DBType {

    MYSQL("com.mysql.cj.jdbc.Driver", "jdbc:mysql://","?useUnicode=true&serverTimezone=UTC&useSSL=false&autoReconnect=true&characterEncoding=utf-8"),
    POSTGRESQL("org.postgresql.Driver", "jdbc:postgresql://",""),
    NULL();
    private String driver;
    private String prefix;
    private String suffix;

    public String getDriver() {
        return driver;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
    DBType(){};
    DBType(String driver, String prefix, String suffix) {
        this.driver = driver;
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
