<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapper.fullName}">
    <!-- 通用查询映射结果 -->
    <resultMap id="${entity.objectName}ResultMap" type="${entity.fullName}">
        <#list table.columns as column>
            <result column="${column.name}" property="${column.objectName}" jdbcType="${column.jdbcType}" javaType="${column.javaType}"/>
        </#list>
    </resultMap>
    <sql id="base_Column_List">
        <#list table.columns as column>${column.name}<#if column_has_next>,</#if></#list>
    </sql>
    <sql id="where_clause_not_null">
        <where><#list table.columns as column><if test="${column.objectName} != null">AND  ${column.name}  = ${r'#{'}${column.objectName}${r'}'}</if></#list></where>
    </sql>
    <sql id="set_clause_not_null">
        <trim prefix=" " prefixOverrides=","><#list table.columns as column><if test="${column.objectName} != null">, ${column.name}  = ${r'#{'}${column.objectName}${r'}'}</if></#list></trim>
    </sql>
    <select id="selectOneForUpdate" parameterType="${entity.fullName}"
            resultMap="${entity.objectName}ResultMap">
        select <include refid="base_Column_List"/> from ${table.name!} t
        <include refid="where_clause_not_null"/>
    </select>
    <#list table.columns as column>
    <#if column.name=="sequence" >
    <update id="updateSequence" parameterType="${entity.fullName}">
        update ${table.name!}  set `sequence` =   ${r'#{sequence}'} where id=${r'#{id}'}
    </update>
    </#if>
    </#list>
</mapper>
