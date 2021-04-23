package ${service.packageName};

import java.util.List;
import ${entity.fullName};
import ${dto.fullName};
import ${mapper.fullName};
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/

public interface ${service.className}  {
/**增**/
${dto.className} create(${dto.className} createDTO);
/**条件查一个并锁定**/
${dto.className} selectOneForUpdate(${dto.className} queryDTO);
/**条件查一个**/
${dto.className} selectOne(${dto.className} queryDTO);
/**条件查一列表**/
List<${dto.className}> selectList(${dto.className} queryDTO);
/**分页查**/
Page<${dto.className}> page(${dto.className} queryDTO);
/**改**/
int updateById(${dto.className} updateDTO);
<#list table.columns as column>
    <#if column.name=="sequence" >
        int updateSequence(${dto.className} updateDTO);
    </#if>
</#list>
/**改**/
int updateByQuery(${dto.className} updateDTO,${dto.className} query);
}
