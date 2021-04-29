package db.mysql.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import db.mysql.entities.GlobalTableIdEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 
* @author Zane
* @since 2021-04-29
*/
@Mapper
public interface GlobalTableIdMapper extends BaseMapper<GlobalTableIdEntity> {
    GlobalTableIdEntity selectOneForUpdate(GlobalTableIdEntity query);
}
