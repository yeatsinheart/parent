package com.db.orm.jdbc;

import com.alibaba.fastjson.JSON;
import com.db.config.ConnectInfo;
import com.db.config.db.Column;
import com.db.config.db.DBSql;
import com.db.config.db.Table;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class JDBCTemplate {
    public static JdbcTemplate getConnect(String driver, String url, String name, String pw) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(pw);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    public static List<Table> getTables(ConnectInfo db, List<String> tables) {
        JdbcTemplate connect = getConnect(db.getDriver().getDriver(), db.getUrl(), db.getUsername(), db.getPassword());
        RowMapper<Column> columnRowMapper = new BeanPropertyRowMapper(Column.class);
        RowMapper<Table> tableRowMapper = new BeanPropertyRowMapper(Table.class);
        List<Table> tableList = connect.query(DBSql.POSTGRESQL_SQL.getTableSql(tables), tableRowMapper);
        tableList.forEach(table -> {
            List<Column> columns = connect.query(DBSql.POSTGRESQL_SQL.getColumnSql(table.getName()), columnRowMapper);
            table.setColumnList(columns);
            System.out.println(JSON.toJSONString(columns));
        });
        return tableList;
    }

    public static void main(String[] args) {
        List<Table> tables =  getTables(ConnectInfo.WX_POSTGRESQL,null);
    }

}
