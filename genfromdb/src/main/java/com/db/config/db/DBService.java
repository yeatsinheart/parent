package com.db.config.db;

import com.db.config.ConnectInfo;
import com.db.config.db.mapper.DBMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DBService {
    private ConcurrentHashMap<String, SqlSessionFactory> dataBaseConnectMap = new ConcurrentHashMap();

    /**
     * 本地获取所有数据表
     */
    public List<Table> getAllTables(ConnectInfo dbinfo, List<String> tables) {
        DBSql baseSql = DBSql.getTableSqlByDriver(dbinfo);
        SqlSessionFactory sqlSessionFactory = getDbConnection(dbinfo);
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        DBMapper tableMapper = template.getMapper(DBMapper.class);
        String sql = baseSql.getTableSql(tables);
        List<Table> tableList = tableMapper.getAllTables(sql);
        return tableList;
    }

    /**
     * 本地获取所有数据表字段
     */
    public List<Column> getAllColumns(ConnectInfo dbinfo, String tableName) {
        DBSql baseSql = DBSql.getTableSqlByDriver(dbinfo);
        SqlSessionFactory sqlSessionFactory = getDbConnection(dbinfo);
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        DBMapper tableMapper = template.getMapper(DBMapper.class);
        List<Column> columnList = tableMapper.getAllColumns(baseSql.getColumnSql(tableName));
        //提交事务
       /* template.commit();
        //关闭session
        template.close();*/
        return columnList;
    }

    /**
     * 连接对应数据库
     */
    public SqlSessionFactory getDbConnection(ConnectInfo dbinfo) {
        SqlSessionFactory sqlSessionFactory = dataBaseConnectMap.get(dbinfo.getUrl());
        if (sqlSessionFactory != null) return sqlSessionFactory;
        DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName(dbinfo.getDriver().getDriver())
                .url(dbinfo.getUrl())
                .username(dbinfo.getUsername())
                .password(dbinfo.getPassword()).build();
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        Configuration configuration = new Configuration();
        configuration.addMappers("com.db.config.db.mapper");
        factoryBean.setConfiguration(configuration);
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/auto/database/config/db/mapper/*Mapper.xml");
        try {
            factoryBean.setMapperLocations(context.getResources("classpath:com/auto/database/config/db/mapper/*Mapper.xml"));
        } catch (IOException e) {
            System.out.println("戳错");
            e.printStackTrace();
        }
        try {
            sqlSessionFactory = factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBaseConnectMap.put(dbinfo.getUrl(), sqlSessionFactory);
        return sqlSessionFactory;
    }
}
