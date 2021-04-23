package ${create.packageName};
import java.io.Serializable;
import com.db.create.handle.anotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list table.columns as column>
    import ${column.javaType};
</#list>
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
@TableName("${table.name!}")
@TableComment("${table.comment!}")
@TablePrefix("")
@TableSuffix("")
@Data
@EqualsAndHashCode(callSuper=false)
public class ${create.className} implements Serializable {

private static final long serialVersionUID = 1L;
<#list table.columns as column>

    /**
    * ${column.comment!}   数据库类型${column.type!}
    */
    <#if column.key!?length gt 0>
        @ColumnComment(" ${column.comment!}")
        @ColumnName("${column.name!}")
        @ColumnId("${column.objectName?uncap_first}")
    <#else>
        @ColumnComment("${column.comment!}")
        @ColumnName("${column.name}")
    </#if>
    @ColumnRawType("${column.type!}")
    private ${column.javaTypeName} ${column.objectName?uncap_first};

</#list>

@Override
public String toString() {
return "${create.className}{" +
<#list table.columns as column>
    <#if column_index==0>
        "  ${column.objectName?uncap_first} :" + ${column.objectName?uncap_first} +
    <#else>
        ", ${column.objectName?uncap_first} :" +${column.objectName?uncap_first} +
    </#if>
</#list>
"}";
}
}
