package com.db.config;

import com.db.config.db.DBType;

import java.util.HashMap;
import java.util.Map;

import static com.db.config.db.DBType.MYSQL;
import static com.db.config.db.DBType.POSTGRESQL;

/**
 * @program: freework
 * @description: this is a 数据库信息
 * @author: Yeats
 * @create: 2019-06-03 14:20
 **/
public class ConnectInfo {

    private DBType driver;
    private String url;
    private String username;
    private String password;

    public static ConnectInfo WX_POSTGRESQL = new ConnectInfo(POSTGRESQL, "203.60.1.50:5432/globaldb_wx", "globaladmin", "2015qaz@123");
    public static ConnectInfo ONLINE_MYSQL = new ConnectInfo(MYSQL, "103.140.151.216:3306/casino", "root", "ofX6sCz26NwHkhyK");
    public static ConnectInfo LOCAL_TEST = new ConnectInfo(MYSQL, "172.16.9.83:3306/casino", "root", "ah9HhAaFvurD3w#");
    public static ConnectInfo LOCAL_MAC_TEST = new ConnectInfo(MYSQL, "localhost:3306/gamming", "root", "qf0K7jFAy");

    public ConnectInfo(DBType driver, String url, String username, String password) {
        this.driver = driver;
        this.url = driver.getPrefix() + url + driver.getSuffix();
        this.username = username;
        this.password = password;
    }
    public Map toMap(){
        Map map = new HashMap();
        map.put("dbType",this.driver.name());
        return map;
    }

    public DBType getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
