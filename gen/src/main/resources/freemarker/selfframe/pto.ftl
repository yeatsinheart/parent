package ${pto.packageName};
import ${dto.fullName};
import java.io.Serializable;
import com.common.dto.BaseRequest;
import org.apache.commons.lang3.StringUtils;

<#list table.columns as column>
    import ${column.javaType};
</#list>
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name="${pto.className}", description="${table.comment!}")
public class ${pto.className} extends BaseRequest  implements Serializable {

private static final long serialVersionUID = 1L;

<#list table.columns as column>
    /**
    * ${column.comment!}   数据库类型${column.type!}
    */
    @Schema(description = "${column.comment!}",name="${column.objectName?uncap_first}",required=${column.notNull!})
    private ${column.javaTypeName} ${column.objectName?uncap_first};
</#list>

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
    return "${pto.className}{" +
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
public String redisKey(){
String key ="";
<#list table.columns as column>
    if(null != this.get${column.objectName?cap_first}())key=key+":${column.name!}_"+this.get${column.objectName?cap_first}();
</#list>
return key;
}
private ${dto.className} convert2DTO(${pto.className} pto){
${dto.className} dto = new ${dto.className}();
<#list table.columns as column>
    dto.set${column.objectName?cap_first}(pto.get${column.objectName?cap_first}());
</#list>
return dto;
}
}
