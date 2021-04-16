package com.db.create.handle.toObject;

import com.db.config.db.Column;
import com.db.config.db.DBType;
import com.db.config.relation.DataBaseJavaRelation;
import com.db.config.relation.MybatisJavaRelation;
import com.db.utils.NameConvertor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class ColumnHandle {
    public static Map toMap(DBType dbType, Column column, String prefix, String suffix){
        Map map = new HashMap();
        map.put("name",column.getName());
        map.put("type",column.getType());
        map.put("raw_type",column.getRaw_type());
        map.put("notNull",column.getNotNull());
        map.put("length",column.getLength());
        map.put("scale",column.getScale());
        map.put("key",column.getKey());
        String c = StringUtils.isBlank(column.getComment())?"":column.getComment();
        map.put("comment",c.replaceAll("\\\r",";").replaceAll("\\\n",";"));
        String className = DataBaseJavaRelation.getRelationByDataBase(dbType).getJavaType(column.getType());
       log.error(column.getType()+"========="+className);
        String[] classNameArray = className.split("\\.");
        map.put("javaType", className);
        map.put("javaTypeName", classNameArray[classNameArray.length-1]);
        map.put("jdbcType", MybatisJavaRelation.java2jdbc.get(map.get("javaType")));

        map.put("nameFormatted",column.getName().replaceAll(prefix,"").replaceAll(suffix,""));
        map.put("objectName",NameConvertor.underlineToCamel((String) map.get("nameFormatted")));
        return map;
    }
}
