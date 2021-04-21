package com.gen.services;

import com.gen.entities.Column;
import com.gen.entities.Table;
import com.gen.mapper.DBMapper;
import com.gen.utils.MysqlUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MysqlService {

    /**
     * 本地获取所有数据表
     */
    public List<Table> getAllTables(SqlSessionFactory sqlSessionFactory, List<String> tables) {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        DBMapper tableMapper = template.getMapper(DBMapper.class);
        String sql = MysqlUtil.getTableSql(tables);
        List<Table> tablees = tableMapper.getAllTables(sql);
        return tablees;
    }

    /**
     * 本地获取所有数据表字段
     */
    public List<Column> getAllColumns(SqlSessionFactory sqlSessionFactory, String tableName) {

        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        DBMapper tableMapper = template.getMapper(DBMapper.class);
        String sql = MysqlUtil.getColumnSql( tableName);
        List<Column> columns = tableMapper.getAllColumns(sql);
        return columns;
    }

}
