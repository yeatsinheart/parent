package com.db.config.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: this is a 数据库表信息
 * @author: Yeats
 * @create: 2019-06-03 14:10
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String name;

    /**
     * 存储引擎
     */
    private String engine;
    /**
     * 备注
     */
    private String comment;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 字段集合
     */
    private List<Column> columnList;

}
