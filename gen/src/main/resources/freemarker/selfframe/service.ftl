package ${service.packageName};

import java.util.List;
import java.lang.Boolean;
import ${dto.fullName};
import org.springframework.beans.factory.annotation.*;
import java.math.BigDecimal;
import com.common.result.Result;
import com.common.result.PageResult;
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/

public interface ${service.className}  {
    /**增**/
    Result<${dto.className}> create(${dto.className} createDTO);
    /**条件查一个并锁定**/
    Result<${dto.className}> selectOneForUpdate(${dto.className} queryDTO);
    /**条件查一个**/
    Result<${dto.className}> selectOne(${dto.className} queryDTO);
    /**查所有**/
    Result<List<${dto.className}>> selectAll();
    /**条件查一列表**/
    Result<List<${dto.className}>> selectList(${dto.className} queryDTO);
    /**分页查**/
    PageResult<${dto.className}> page(${dto.className} queryDTO);
    /**改**/
    Result<Boolean> updateById(${dto.className} updateDTO);
    <#list table.columns as column>
    <#if column.name=="sequence" >
    Result<Integer> updateSequence(${dto.className} updateDTO);
        </#if>
        </#list>
    /**改**/
    Result<Integer> updateByQuery(${dto.className} updateDTO,${dto.className} query);
}
