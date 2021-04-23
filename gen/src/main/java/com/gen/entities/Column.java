package com.gen.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description: this is a 表信息
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名
     */
    private String name;
    /**
     * 类型
     */
    private String raw_type;
    /**
     * 类型
     */
    private String type;
    /**
     * 长度
     */
    private Integer length;
    /**
     * 小数点
     */
    private Integer scale;
    /**
     * 不是空
     */
    private String notNull;
    /**
     * 是否主键
     */
    private String key;
    /**
     * 注释
     */
    private String comment;


}
