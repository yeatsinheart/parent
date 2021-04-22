package ${serviceImpl.packageName};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import java.lang.Integer;
import com.common.result.ResultGenerator;
import java.util.ArrayList;
import ${entity.fullName};
import ${dto.fullName};
import ${dao.fullName};
import ${service.fullName};
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;

import com.common.result.Result;
import java.util.Set;
<#if cacheable >
import com.redis.RedisService;
import com.redis.RedisResult;
</#if>
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
public class ${serviceImpl.className} implements ${service.className} {

<#if lombok >
<#else >
    private static final Log log = LogFactory.getLog(${serviceImpl.className}.class);
</#if>
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private  ${dao.className} dao;
<#if cacheable >
    @Autowired
    RedisService redisService;
</#if>

    /**增**/
    @Override
    public Result<${dto.className}> create(${dto.className} createDTO){
        //String redisKey = "${dto.project}:${service.className}:selectOne" + createDTO.redisKey();
        //redisService.delete(redisKey);
        ${entity.className} entity =convert2Entity(createDTO);
        entity = dao.create(entity);
        if(null != entity){
            Set<String> keys = redisService.keys("${dto.project}:${service.className}:*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisService.delete((String[])keys.toArray());
            }
            return ResultGenerator.genSuccessResult(convert2DTO(entity),createDTO.getRequestLanguage());
        }
        return null;
    }
    /**条件查一个**/
    public Result<${dto.className}> selectOne(${dto.className} queryDTO){
<#if cacheable >
        String redisKey = "${dto.project}:${service.className}:selectOne" + queryDTO.redisKey();
        ${entity.className} entity = redisService.get(redisKey, ${entity.className}.class);
        if (null == entity) {
            entity = dao.selectOne(convert2Entity(queryDTO));
            if(null == entity){
                return null;
            }
            redisService.set(redisKey, entity, 10 * 60);
        }
<#else>
        entity = dao.selectOne(convert2Entity(queryDTO));
        if(null == entity){
            return null;
        }
</#if>
        return ResultGenerator.genSuccessResult(convert2DTO(entity),queryDTO.getRequestLanguage());
    }
    /**条件查一个并锁定**/
    public Result<${dto.className}> selectOneForUpdate(${dto.className} queryDTO){
        ${entity.className} entity = dao.selectOneForUpdate(convert2Entity(queryDTO));
        if(null == entity){
            return null;
        }
        return  ResultGenerator.genSuccessResult(convert2DTO(entity),queryDTO.getRequestLanguage());
    }

    /**条件查一列表**/
    public Result<List<${dto.className}>> selectList(${dto.className} queryDTO){
        <#if cacheable >
        String redisKey = "${dto.project}:${service.className}:selectList" + queryDTO.redisKey();
        RedisResult<${dto.className}> listResult = redisService.getListResult(redisKey, ${dto.className}.class);
        List<${dto.className}> dtos =listResult.getListResult();
        if( null == listResult || !listResult.isExist() || CollectionUtils.isEmpty(dtos)){
            List<${entity.className}> entities = dao.selectList(convert2Entity(queryDTO));
            if(CollectionUtils.isEmpty(entities)){
                return ResultGenerator.genSuccessResult(dtos,queryDTO.getRequestLanguage());
            }
            dtos = new ArrayList();
            for (${entity.className} entity : entities) {
                dtos.add(convert2DTO(entity));
            }
            redisService.set(redisKey, dtos, 10 * 60);
        }
        <#else>
        List<${entity.className}> entities = dao.selectList(convert2Entity(queryDTO));
        if(CollectionUtils.isEmpty(entities)){
            return dtos;
        }
        List<${dto.className}> dtos = new ArrayList();
        for(${entity.className} entity:entities){
            dtos.add(convert2DTO(entity));
        }
        </#if>
        return ResultGenerator.genSuccessResult(dtos,queryDTO.getRequestLanguage());
    }
    /**分页查**/
    @Override
    public Result<Page<${dto.className}>> page(${dto.className} queryDTO){
        Page<${entity.className}> page = dao.page( convert2Entity(queryDTO),queryDTO.getRequestPageSize(),queryDTO.getRequestPageNum());
        List<${dto.className}> dtos = new ArrayList();
        for (${entity.className} entity : page.getRecords()) {
            dtos.add(convert2DTO(entity));
        }
        Page<${dto.className}> pageDTO = new Page();
        pageDTO.setSize(page.getSize());
        pageDTO.setCurrent(page.getCurrent());
        pageDTO.setTotal(page.getTotal());
        pageDTO.setPages(page.getPages());
        pageDTO.setOrders(page.getOrders());
        pageDTO.setRecords(dtos);
        return ResultGenerator.genSuccessResult(pageDTO,queryDTO.getRequestLanguage());
    }
    /**改**/
    @Override
    public Result<Integer> updateById(${dto.className} updateDTO){
        int updatedNum = dao.updateById(convert2Entity(updateDTO));
        if(updatedNum>0){
            Set<String> keys = redisService.keys("${dto.project}:${service.className}:*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisService.delete((String[])keys.toArray());
            }
        }
        return  ResultGenerator.genSuccessResult(updatedNum,updateDTO.getRequestLanguage());
    }
    /**改**/
    @Override
    public Result<Integer> updateByQuery(${dto.className} updateDTO,${dto.className} query){
        int updatedNum =  dao.updateByQuery( convert2Entity(updateDTO), convert2Entity(query));
        if(updatedNum>0){
            Set<String> keys = redisService.keys("${dto.project}:${service.className}:*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisService.delete((String[])keys.toArray());
            }
        }
        return  ResultGenerator.genSuccessResult(updatedNum,query.getRequestLanguage());
    }
<#list table.columns as column>
    <#if column.name=="sequence" >
    @Override
    public Result<Integer> updateSequence(${dto.className} updateDTO){
        int updatedNum = dao.updateSequence(convert2Entity(updateDTO));
        if(updatedNum>0){
            Set<String> keys = redisService.keys("${dto.project}:${service.className}:*");
            if (!CollectionUtils.isEmpty(keys)) {
                redisService.delete((String[])keys.toArray());
            }
        }
        return  ResultGenerator.genSuccessResult(updatedNum,updateDTO.getRequestLanguage());
    }
    </#if>
</#list>

     private ${dto.className} convert2DTO(${entity.className} entity){
         ${dto.className} dto = new ${dto.className}();
         <#list table.columns as column>
         dto.set${column.objectName?cap_first}(entity.get${column.objectName?cap_first}());
         </#list>
         return dto;
     }
     private  ${entity.className} convert2Entity(${dto.className} dto){
         ${entity.className} entity = new ${entity.className}();
         <#list table.columns as column>
         entity.set${column.objectName?cap_first}(dto.get${column.objectName?cap_first}());
         </#list>
         return entity;
     }
}