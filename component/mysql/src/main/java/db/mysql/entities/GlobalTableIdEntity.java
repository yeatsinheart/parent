package db.mysql.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;

/**
 * @author Zane
 * @since 2021-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("global_table_id")
public class GlobalTableIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键  数据库类型int PRI
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 主键ID  数据库类型bigint
     */
    @TableField(value = "gen_id", jdbcType = JdbcType.UNDEFINED)
    private Long genId;
    /**
     * 表  数据库类型varchar(255)
     */
    @TableField(value = "table", jdbcType = JdbcType.UNDEFINED)
    private String table;

}
