package ${mapper.packageName};

import java.util.List;
import ${entity.fullName};
import ${dto.fullName};
import java.math.BigDecimal;
import org.apache.ibatis.annotations.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
@Mapper
public interface ${mapper.className}  extends BaseMapper<${entity.className}> {

}
