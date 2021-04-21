package com.gen.mapper;

import com.gen.entities.Column;
import com.gen.entities.Table;
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
