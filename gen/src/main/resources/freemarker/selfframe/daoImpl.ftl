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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ${daoImpl.className} extends ServiceImpl<${mapper.className},${entity.className}> implements ${dao.className} {

<#if lombok >
<#else >
    private static final Log log = LogFactory.getLog(${daoImpl.className}.class);
</#if>

    /**增**/
    @Override
    public ${entity.className} create(${entity.className} createEntity){
        int insertNum = getBaseMapper().insert(createEntity);
        if(insertNum==1){
            return createEntity;
        }
        return null;
    }
    /**条件查一个**/
    public ${entity.className} selectOne(${entity.className} queryEntity){
        ${entity.className} entity = getBaseMapper().selectOne(getQueryWrapperNotNull(queryEntity));
        return entity;
    }
    /**条件查一个并锁定**/
    public ${entity.className} selectOneForUpdate(${entity.className} queryEntity){
        ${entity.className} entity = getBaseMapper().selectOneForUpdate(queryEntity);
        return entity;
    }
    /**查所有**/
    public List<${entity.className}> selectList(){
        List<${entity.className}> entities = list();
        return entities;
    }
    /**条件查一列表**/
    public List<${entity.className}> selectList(${entity.className} queryEntity){
        List<${entity.className}> entities = getBaseMapper().selectList(getQueryWrapperNotNull(queryEntity));
        return entities;
    }
    /**分页查**/
    @Override
    public Page<${entity.className}> page(${entity.className} queryEntity,long pageSize,long pageNum){
        Page<${entity.className}> page = new Page(pageNum,pageSize);
        page = getBaseMapper().selectPage(page, getQueryWrapperNotNull(queryEntity));
        return page;
    }
    /**改**/
    @Override
    public int updateByQuery(${entity.className} updateEntity,${entity.className} query){
        int updatedNum =  getBaseMapper().update( updateEntity, getQueryWrapperNotNull(query));
        return  updatedNum;
    }
<#list table.columns as column>
    <#if column.name=="sequence" >
    @Override
    public int updateSequence(${entity.className} updateEntity){
        int updatedNum = getBaseMapper().updateSequence(updateEntity);
        return  updatedNum;
    }
    </#if>
</#list>

    Wrapper<${entity.className}> getQueryWrapperNotNull(${entity.className} query){
        QueryWrapper<${entity.className}> queryWrapper = new QueryWrapper();
        <#list table.columns as column>
        if(null != query.get${column.objectName?cap_first}())queryWrapper.eq("${column.name!}",query.get${column.objectName?cap_first}());
        </#list>
        return queryWrapper;
    }
}