package ${service.packageName};

import java.util.List;
import ${entity.fullName};
import ${dto.fullName};
import ${mapper.fullName};
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.*;
import java.math.BigDecimal;
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/

public interface ${service.className}  {

    /**
    * 分页获取数据列表
    */
    List< ${entity.className}> list${entity.className}(${dto.className} ${dto.className?uncap_first});
    
    
    /**
    * 获取全部数据
    */
    List< ${entity.className}> all${entity.className}();
    
    
    /**
    * 根据ID查找数据
    */
     ${entity.className} get${entity.className}(${dto.className} ${dto.className?uncap_first});
    
    
    /**
    * 根据条件查找数据
    */
    List< ${entity.className}>  find${entity.className}( ${dto.className} ${dto.className?uncap_first});
    
    
    /**
    * 添加数据
    */
    int add${entity.className}( ${dto.className} ${dto.className?uncap_first});
    
    
    /**
    * 更新数据
    */
    int update${entity.className}( ${dto.className} ${dto.className?uncap_first});

    /**
    * 几条数据
    */
    Long count${entity.className}( ${dto.className} ${dto.className?uncap_first});

    /**
    * 统计 某个字段
    */
    BigDecimal sum${entity.className}( ${dto.className} ${dto.className?uncap_first});
}
