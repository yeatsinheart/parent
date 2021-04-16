package ${dto.packageName};
import ${entity.fullName};
import java.io.Serializable;
<#list table.columns as column>
import ${column.javaType};
</#list>
<#if swagger >
import io.swagger.annotations.*;
</#if>
<#if lombok >
import lombok.Data;
import lombok.EqualsAndHashCode;
</#if>
/**
 * ${table.comment!}
 * @author ${author}
 * @since ${time}
 */
<#if lombok >
@Data
@EqualsAndHashCode(callSuper=false)
</#if>
<#if swagger >
@ApiModel("${table.comment!}")
</#if>
public class ${dto.className} implements Serializable {

    private static final long serialVersionUID = 1L;
<#if swagger >
    @ApiModelProperty(value="用户TOKEN",name="token",required=true)
</#if>
    /**应用ID**/
    private Long appId;
<#if swagger >
    @ApiModelProperty(value="页数",name="pageIndex",required=true)
</#if>
    /**当前页**/
    private Long page;
<#if swagger >
    @ApiModelProperty(value="每页展示数量",name="pageSize",required=true)
</#if>
    /**每页展示数量**/
    private Long size;
<#list table.columns as column>
    /**
    * ${column.comment!}   数据库类型${column.type!}
    */
    <#if swagger >
    @ApiModelProperty(value="${column.comment!}",name="${column.objectName?uncap_first}",required=${column.notNull!})
    </#if>
    private ${column.javaTypeName} ${column.objectName?uncap_first};
</#list>
    public ${entity.className} convert2Entity(){
    ${entity.className} entity = new ${entity.className}();
    <#list table.columns as column>
        entity.set${column.objectName?cap_first}(this.get${column.objectName?cap_first}());
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

    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public Long getDateOffset() {
        return this.dateOffset;
    }
    public void setDateOffset(Long dateOffset) {
        this.dateOffset = dateOffset;
    }

    public Long getPageIndex() {
        return this.pageIndex;
    }
    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageSize() {
        return this.pageSize;
    }
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "${dto.className}{" +
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
