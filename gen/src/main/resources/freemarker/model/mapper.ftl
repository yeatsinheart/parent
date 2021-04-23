package ${mapper.packageName};

import java.util.List;
import ${entity.fullName};
import ${dto.fullName};
import java.math.BigDecimal;
import org.apache.ibatis.annotations.*;
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
@Mapper
public interface ${mapper.className}  extends BaseMapper<${entity.className}> {
/**
* 分页获取数据列表
*/
List< ${entity.className}> list${entity.className}(${entity.className} ${entity.className?uncap_first});


/**
* 获取全部数据
*/
List< ${entity.className}> all${entity.className}();


/**
* 根据ID查找数据
*/
${entity.className} get${entity.className}(${entity.className} ${entity.className?uncap_first});


/**
* 根据条件查找数据
*/
List< ${entity.className}>  find${entity.className}( ${entity.className} ${entity.className?uncap_first});


/**
* 添加数据
*/
int add${entity.className}( ${entity.className} ${entity.className?uncap_first});


/**
* 更新数据
*/
int update${entity.className}( ${entity.className} ${entity.className?uncap_first});

/**
* 几条数据
*/
Long count${entity.className}( ${entity.className} ${entity.className?uncap_first});

/**
* 统计 某个字段
*/
BigDecimal sum${entity.className}( ${entity.className} ${entity.className?uncap_first});
}
