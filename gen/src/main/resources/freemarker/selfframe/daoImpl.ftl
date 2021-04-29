package ${daoImpl.packageName};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import java.util.ArrayList;
import ${entity.fullName};
import ${mapper.fullName};
import ${dao.fullName};
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;
import com.db.config.BaseDao;
import java.util.Set;
<#if lombok >
import lombok.extern.slf4j.Slf4j;
</#if>
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
<#if lombok >
@Slf4j
</#if>
@Service
public class ${daoImpl.className} extends BaseDao<${mapper.className},${entity.className}> implements ${dao.className} {

<#if lombok >
<#else >
    private static final Log log = LogFactory.getLog(${daoImpl.className}.class);
</#if>

    /**条件查一个并锁定**/
    public ${entity.className} selectOneForUpdate(${entity.className} queryEntity){
        ${entity.className} entity = getBaseMapper().selectOneForUpdate(queryEntity);
        return entity;
    }
<#list table.columns as column>
    <#if column.name=="sequence" >
    @Override
    public int updateSequence(${entity.className} updateEntity){
        int updatedNum = this.getBaseMapper().updateSequence(updateEntity);
        return  updatedNum;
    }
    </#if>
</#list>

    protected Wrapper<${entity.className}> getQueryWrapperNotNull(${entity.className} query){
        QueryWrapper<${entity.className}> queryWrapper = new QueryWrapper();
<#list table.columns as column>
        if(null != query.get${column.objectName?cap_first}())queryWrapper.eq("${column.name!}",query.get${column.objectName?cap_first}());
</#list>
        return queryWrapper;
    }
}