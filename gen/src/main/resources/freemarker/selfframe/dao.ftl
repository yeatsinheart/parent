package ${dao.packageName};

import java.util.List;
import ${entity.fullName};
import org.springframework.beans.factory.annotation.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/

public interface ${dao.className}  {
    /**增**/
    ${entity.className} create(${entity.className} createEntity);
    /**条件查一个并锁定**/
    ${entity.className} selectOneForUpdate(${entity.className} queryEntity);
    /**条件查一个**/
    ${entity.className} selectOne(${entity.className} queryEntity);
    /**条件查一列表**/
    List<${entity.className}> selectList(${entity.className} queryEntity);
    /**分页查**/
    Page<${entity.className}> page(${entity.className} queryEntity,long pageSize,long pageNum);
    /**改**/
    int updateById(${entity.className} updateEntity);
<#list table.columns as column>
<#if column.name=="sequence" >
    int updateSequence(${entity.className} updateEntity);
</#if>
</#list>
    /**改**/
    int updateByQuery(${entity.className} updateEntity,${entity.className} query);


}
