package ${serviceImpl.packageName};
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import ${entity.fullName};
import ${dto.fullName};
import ${mapper.fullName};
import ${service.fullName};
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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
@Autowired
private  ${mapper.className} ${mapper.className?uncap_first};

Wrapper<${dto.className}> getQueryWrapper(${dto.className} query){
QueryWrapper<${dto.className}> queryWrapper = new QueryWrapper();
<#list table.columns as column>
    if(null != query.get${column.objectName?cap_first}())queryWrapper.eq("${column.name!}",query.get${column.objectName?cap_first}());
</#list>
return queryWrapper;
}
}
