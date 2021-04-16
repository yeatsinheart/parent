package ${serviceImpl.packageName};

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
    /**
    * 分页获取数据列表
    */
    public List< ${entity.className}> list${entity.className}(${dto.className} ${dto.className?uncap_first}){
        return ${mapper.className?uncap_first}.list${entity.className}(${dto.className?uncap_first});
    }
    
    
    /**
    * 获取全部数据
    */
    public List< ${entity.className}> all${entity.className}(){
        return ${mapper.className?uncap_first}.all${entity.className}();
    }
    
    
    /**
    * 根据ID查找数据
    */
    public ${entity.className} get${entity.className}(${dto.className} ${dto.className?uncap_first}){
        return ${mapper.className?uncap_first}.get${entity.className}(${dto.className?uncap_first});
    }
    
    /**
    * 根据条件查找数据
    */
    public List< ${entity.className}>  find${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        return ${mapper.className?uncap_first}.find${entity.className}(${dto.className?uncap_first});
    }
    
    /**
    * 添加数据
    */
    public int add${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        return ${mapper.className?uncap_first}.add${entity.className}(${dto.className?uncap_first});
    }
    
    /**
    * 更新数据
    */
    public int update${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        return ${mapper.className?uncap_first}.update${entity.className}(${dto.className?uncap_first});
    }
    /**
    * 几条数据
    */
    public Long count${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        return ${mapper.className?uncap_first}.count${entity.className}(${dto.className?uncap_first});
    }
    /**
    * 统计 某个字段
    */
    public BigDecimal sum${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        return ${mapper.className?uncap_first}.sum${entity.className}(${dto.className?uncap_first});
    }
}
