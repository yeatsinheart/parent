package ${mapper.packageName};

import java.util.List;
import ${entity.fullName};
import java.math.BigDecimal;
import org.apache.ibatis.annotations.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
@Mapper
public interface ${mapper.className}  extends BaseMapper<${entity.className}> {
    ${entity.className} selectOneForUpdate(${entity.className} query);
<#list table.columns as column>
    <#if column.name=="sequence" >
    int updateSequence(${entity.className} updateDTO);
    </#if>
</#list>
}
