package ${serviceImpl.packageName};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import java.lang.Integer;
import com.common.result.ResultGenerator;

import com.common.constant.Language;
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
import com.common.result.PageResult;
import com.common.result.Result;
import org.apache.dubbo.config.annotation.DubboService;
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
@DubboService
public class ${serviceImpl.className} implements ${service.className} {

<#if lombok >
<#else >
    private static final Log log = LogFactory.getLog(${serviceImpl.className}.class);
</#if>
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Autowired
private ${dao.className} ${table.objectName}Dao;
<#if cacheable >
    @Autowired
    private RedisService redisService;
</#if>

/**增**/
@Override
public Result<${dto.className}> create(${dto.className} createDTO){
//String redisKey = "${dto.project}:${service.className}:selectOne" + createDTO.redisKey();
//redisService.delete(redisKey);
${entity.className} entity =convert2Entity(createDTO);
if(${table.objectName}Dao.save(entity)){
Set
<String> keys = redisService.keys("${dto.project}:${service.className}:*");
    if (!CollectionUtils.isEmpty(keys)) {
    redisService.delete((String[])keys.toArray());
    }
    return ResultGenerator.genSuccessResult(convert2DTO(entity),createDTO.getRequestLanguage());
    }
    return ResultGenerator.genFailResult(createDTO.getRequestLanguage());
    }
    /**条件查一个**/
    public Result<${dto.className}> selectOne(${dto.className} queryDTO){
    <#if cacheable >
        String redisKey = "${dto.project}:${service.className}:selectOne" + queryDTO.redisKey();
        ${entity.className} entity = redisService.get(redisKey, ${entity.className}.class);
        if (null == entity) {
        entity = ${table.objectName}Dao.getOne(convert2Entity(queryDTO));
        if(null == entity){
        return ResultGenerator.genFailResult(queryDTO.getRequestLanguage());
        }
        redisService.set(redisKey, entity, 10 * 60);
        }
    <#else>
        entity = ${table.objectName}Dao.getOne(convert2Entity(queryDTO));
        if(null == entity){
        return ResultGenerator.genFailResult(queryDTO.getRequestLanguage());
        }
    </#if>
    return ResultGenerator.genSuccessResult(convert2DTO(entity),queryDTO.getRequestLanguage());
    }
    /**条件查一个并锁定**/
    public Result<${dto.className}> selectOneForUpdate(${dto.className} queryDTO){
    ${entity.className} entity = ${table.objectName}Dao.selectOneForUpdate(convert2Entity(queryDTO));
    if(null == entity){
    return ResultGenerator.genFailResult(queryDTO.getRequestLanguage());
    }
    return ResultGenerator.genSuccessResult(convert2DTO(entity),queryDTO.getRequestLanguage());
    }
    /**查所有**/
    public Result
    <List
    <${dto.className}>> selectAll(){
    List<${entity.className}> entities = ${table.objectName}Dao.list();
    if(CollectionUtils.isEmpty(entities)){
    return ResultGenerator.genFailResult(Language.中文.getCode());
    }
    List<${dto.className}> dtos = new ArrayList();
    for (${entity.className} entity : entities) {
    dtos.add(convert2DTO(entity));
    }
    return ResultGenerator.genSuccessResult(dtos,Language.中文.getCode());
    }
    /**条件查一列表**/
    public Result
    <List
    <${dto.className}>> selectList(${dto.className} queryDTO){
    <#if cacheable >
        String redisKey = "${dto.project}:${service.className}:selectList" + queryDTO.redisKey();
        RedisResult<${dto.className}> listResult = redisService.getListResult(redisKey, ${dto.className}.class);
        List<${dto.className}> dtos =listResult.getListResult();
        if( null == listResult || !listResult.isExist() || CollectionUtils.isEmpty(dtos)){
        List<${entity.className}> entities = ${table.objectName}Dao.list(convert2Entity(queryDTO));
        if(CollectionUtils.isEmpty(entities)){
        return ResultGenerator.genFailResult(queryDTO.getRequestLanguage());
        }
        dtos = new ArrayList();
        for (${entity.className} entity : entities) {
        dtos.add(convert2DTO(entity));
        }
        redisService.set(redisKey, dtos, 10 * 60);
        }
    <#else>
        List<${entity.className}> entities = ${table.objectName}Dao.list(convert2Entity(queryDTO));
        if(CollectionUtils.isEmpty(entities)){
        return ResultGenerator.genFailResult(queryDTO.getRequestLanguage());
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
    public PageResult<${dto.className}> page(${dto.className} queryDTO){
    Page<${entity.className}> page = ${table.objectName}
    Dao.page(convert2Entity(queryDTO),queryDTO.getRequestPageSize(),queryDTO.getRequestPageNum());
    List<${dto.className}> dtos = new ArrayList();
    for (${entity.className} entity : page.getRecords()) {
    dtos.add(convert2DTO(entity));
    }
    return
    ResultGenerator.genPageResult(page.getSize(),page.getCurrent(),page.getTotal(),page.getPages(),page.getRecords(),queryDTO.getRequestLanguage());
    }
    /**改**/
    @Override
    public Result
    <Boolean> updateById(${dto.className} updateDTO){
        Boolean updated = ${table.objectName}Dao.updateById(convert2Entity(updateDTO));
        if(updated){
        Set
        <String> keys = redisService.keys("${dto.project}:${service.className}:*");
            if (!CollectionUtils.isEmpty(keys)) {
            redisService.delete((String[])keys.toArray());
            }
            }
            return ResultGenerator.genSuccessResult(updated,updateDTO.getRequestLanguage());
            }
            /**改**/
            @Override
            public Result
            <Integer> updateByQuery(${dto.className} updateDTO,${dto.className} query){
                int updatedNum = ${table.objectName}Dao.updateByQuery( convert2Entity(updateDTO),convert2Entity(query));
                if(updatedNum>0){
                Set
                <String> keys = redisService.keys("${dto.project}:${service.className}:*");
                    if (!CollectionUtils.isEmpty(keys)) {
                    redisService.delete((String[])keys.toArray());
                    }
                    }
                    return ResultGenerator.genSuccessResult(updatedNum,query.getRequestLanguage());
                    }
                    <#list table.columns as column>
                    <#if column.name=="sequence" >
                    @Override
                    public Result
                    <Integer> updateSequence(${dto.className} updateDTO){
                        int updatedNum = ${table.objectName}Dao.updateSequence(convert2Entity(updateDTO));
                        if(updatedNum>0){
                        Set
                        <String> keys = redisService.keys("${dto.project}:${service.className}:*");
                            if (!CollectionUtils.isEmpty(keys)) {
                            redisService.delete((String[])keys.toArray());
                            }
                            }
                            return ResultGenerator.genSuccessResult(updatedNum,updateDTO.getRequestLanguage());
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
                            private ${entity.className} convert2Entity(${dto.className} dto){
                            ${entity.className} entity = new ${entity.className}();
                            <#list table.columns as column>
                                entity.set${column.objectName?cap_first}(dto.get${column.objectName?cap_first}());
                            </#list>
                            return entity;
                            }
                            }