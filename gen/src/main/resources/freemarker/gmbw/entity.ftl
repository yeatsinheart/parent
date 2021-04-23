package ${entity.packageName};
import ${dto.fullName};
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import org.apache.ibatis.type.JdbcType;
<#list table.columns as column>
    import ${column.javaType};
</#list>
<#if lombok >
    import lombok.Data;
    import lombok.EqualsAndHashCode;
</#if>
<#if swagger >
    import io.swagger.annotations.*;
</#if>
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
<#if swagger >
    @ApiModel("${table.comment!}")
</#if>
<#if lombok >
    @Data
    @EqualsAndHashCode(callSuper=false)
</#if>
@TableName("${table.name!}")
public class ${entity.className} implements Serializable {
private static final long serialVersionUID = 1L;
<#list table.columns as column>
    /**
    * ${column.comment!}  数据库类型${column.type!} ${column.key!}
    */
    <#if swagger >
        @ApiModelProperty(value="${column.comment!}",name="${column.objectName?uncap_first}",required=${column.notNull!})
    </#if>
    <#if column.key=="PRI" >
        @TableId(value="${column.name!}", type = IdType.AUTO)
    <#else >
        @TableField(value="${column.name!}", jdbcType = JdbcType.UNDEFINED)
    </#if>
    private ${column.javaTypeName} ${column.objectName?uncap_first};
</#list>
public ${dto.className} convert2DTO(){
${dto.className} dto = new ${dto.className}();
<#list table.columns as column>
    dto.set${column.objectName?cap_first}(this.get${column.objectName?cap_first}());
</#list>
return dto;
}
public static ${entity.className} convert2Entity(${dto.className} dto){
${entity.className} entity = new ${entity.className}();
<#list table.columns as column>
    entity.set${column.objectName?cap_first}(dto.get${column.objectName?cap_first}());
</#list>
return entity;
}
<#if !lombok >


    <#list table.columns as column>

        public ${column.javaTypeName} get${column.objectName?cap_first}() {
        return ${column.objectName?uncap_first};
        }
        public void set${column.objectName?cap_first}(${column.javaTypeName} ${column.objectName?uncap_first}) {
        this.${column.objectName?uncap_first} = ${column.objectName?uncap_first};
        }
    </#list>

    @Override
    public String toString() {
    return "${entity.className}{" +
    <#list table.columns as column>
        <#if column_index==0>
            "  ${column.objectName?uncap_first} :" + ${column.objectName?uncap_first} +
        <#else>
            ", ${column.objectName?uncap_first} :" +${column.objectName?uncap_first} +
        </#if>
    </#list>
    "}";
    }
</#if>
}
