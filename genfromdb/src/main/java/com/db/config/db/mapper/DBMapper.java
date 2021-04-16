package com.db.config.db.mapper;

import com.db.config.db.Column;
import com.db.config.db.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DBMapper {
    @Select("${sql}")
    List<Column> getAllColumns(@Param(value = "sql") String sql);

    @Select("${sql}")
    List<Table> getAllTables(@Param(value = "sql") String sql);
}
