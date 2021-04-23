package ${serviceImpl.packageName};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import java.util.ArrayList;
import ${entity.fullName};
import ${dto.fullName};
import ${mapper.fullName};
import ${service.fullName};
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;

import java.util.Set;
<#if cacheable >
    import com.game.common.service.RedisService;
    import com.game.common.service.RedisResult;
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
private  ${mapper.className} mapper;
<#if cacheable >
    @Autowired
    RedisService redisService;
</#if>

/**增**/
@Override
public ${dto.className} create(${dto.className} createDTO){
//String redisKey = "${dto.project}:${service.className}:selectOne" + createDTO.redisKey();
//redisService.delete(redisKey);
${entity.className} entity =${entity.className}.convert2Entity(createDTO);
int insertNum = mapper.insert(entity);
if(insertNum==1){
Set
<String> keys = redisService.keys("gamming:${service.className}:*");
    if (!CollectionUtils.isEmpty(keys)) {
    redisService.delete(keys.toArray(String[]::new));
    }
    return entity.convert2DTO();
    }
    return null;
    }
    /**条件查一个**/
    public ${dto.className} selectOne(${dto.className} queryDTO){
    <#if cacheable >
        String redisKey = "${dto.project}:${service.className}:selectOne" + queryDTO.redisKey();
        ${entity.className} entity = redisService.get(redisKey, ${entity.className}.class);
        if (null == entity) {
        entity = mapper.selectOne(getQueryWrapperNotNull(queryDTO));
        if(null == entity){
        return null;
        }
        redisService.set(redisKey, entity, 10 * 60);
        }
    <#else>
        entity = mapper.selectOne(getQueryWrapperNotNull(queryDTO));
        if(null == entity){
        return null;
        }
    </#if>
    return entity.convert2DTO();
    }
    /**条件查一个并锁定**/
    public ${dto.className} selectOneForUpdate(${dto.className} queryDTO){
    ${entity.className} entity = mapper.selectOneForUpdate(${entity.className}.convert2Entity(queryDTO));
    if(null == entity){
    return null;
    }
    return entity.convert2DTO();
    }

    /**条件查一列表**/
    public List<${dto.className}> selectList(${dto.className} queryDTO){
    <#if cacheable >
        String redisKey = "${dto.project}:${service.className}:selectList" + queryDTO.redisKey();
        RedisResult<${dto.className}> listResult = redisService.getListResult(redisKey, ${dto.className}.class);
        List<${dto.className}> dtos =listResult.getListResult();
        if( null == listResult || !listResult.isExist() || CollectionUtils.isEmpty(dtos)){
        List<${entity.className}> entities = mapper.selectList(getQueryWrapperNotNull(queryDTO));
        if(CollectionUtils.isEmpty(entities)){
        return dtos;
        }
        dtos = new ArrayList();
        for (${entity.className} entity : entities) {
        dtos.add(entity.convert2DTO());
        }
        redisService.set(redisKey, dtos, 10 * 60);
        }
    <#else>
        List<${entity.className}> entities = mapper.selectList(getQueryWrapperNotNull(queryDTO));
        if(CollectionUtils.isEmpty(entities)){
        return dtos;
        }
        List<${dto.className}> dtos = new ArrayList();
        for(${entity.className} entity:entities){
        dtos.add(entity.convert2DTO());
        }
    </#if>
    return dtos;
    }
    /**分页查**/
    @Override
    public Page<${dto.className}> page(${dto.className} queryDTO){
    Page<${entity.className}> page = new Page();
    page.setSize(queryDTO.getPageSize());
    page.setCurrent(queryDTO.getPageNum());
    page = mapper.selectPage(page, getQueryWrapperNotNull(queryDTO));
    List<${dto.className}> dtos = new ArrayList();
    for (${entity.className} entity : page.getRecords()) {
    dtos.add(entity.convert2DTO());
    }
    Page<${dto.className}> pageDTO = new Page();
    pageDTO.setSize(page.getSize());
    pageDTO.setCurrent(page.getCurrent());
    pageDTO.setTotal(page.getTotal());
    pageDTO.setPages(page.getPages());
    pageDTO.setOrders(page.getOrders());
    pageDTO.setRecords(dtos);
    return pageDTO;
    }
    /**改**/
    @Override
    public int updateById(${dto.className} updateDTO){
    int updatedNum = mapper.updateById(${entity.className}.convert2Entity(updateDTO));
    if(updatedNum>0){
    Set
    <String> keys = redisService.keys("gamming:${service.className}:*");
        if (!CollectionUtils.isEmpty(keys)) {
        redisService.delete(keys.toArray(String[]::new));
        }
        }
        return updatedNum;
        }
        /**改**/
        @Override
        public int updateByQuery(${dto.className} updateDTO,${dto.className} query){
        int updatedNum = mapper.update( ${entity.className}.convert2Entity(updateDTO), getQueryWrapperNotNull(query));
        if(updatedNum>0){
        Set
        <String> keys = redisService.keys("gamming:${service.className}:*");
            if (!CollectionUtils.isEmpty(keys)) {
            redisService.delete(keys.toArray(String[]::new));
            }
            }
            return updatedNum;
            }
            <#list table.columns as column>
            <#if column.name=="sequence" >
            @Override
            public int updateSequence(${dto.className} updateDTO){
            int updatedNum = mapper.updateSequence(${entity.className}.convert2Entity(updateDTO));
            if(updatedNum>0){
            Set
            <String> keys = redisService.keys("gamming:${service.className}:*");
                if (!CollectionUtils.isEmpty(keys)) {
                redisService.delete(keys.toArray(String[]::new));
                }
                }
                return updatedNum;
                }
                </#if>
                </#list>

                Wrapper<${entity.className}> getQueryWrapperNotNull(${dto.className} query){
                QueryWrapper<${entity.className}> queryWrapper = new QueryWrapper();
                <#list table.columns as column>
                    if(null != query.get${column.objectName?cap_first}())queryWrapper.eq("${column.name!}",query.get${column.objectName?cap_first}());
                </#list>
                return queryWrapper;
                }
                }