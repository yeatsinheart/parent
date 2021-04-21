<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapper.fullName}">
    <!-- 通用查询映射结果 -->
    <resultMap id="${entity.className?uncap_first}ResultMap" type="${entity.fullName}">
        <#list table.columns as column>
            <result column="${column.name}" property="${column.objectName}" jdbcType="${column.jdbcType}" javaType="${column.javaType}"/>
        </#list>
    </resultMap>
    
    <sql id="Base_Column_List">
                <#list table.columns as column>
                    ${column.name}<#if column_has_next>,</#if>
                </#list>
    </sql>
    <sql id="Where_Clause">
            <where>
                <#list table.columns as column>
                    <if test="${column.objectName} != null">
                        AND  ${column.name}  = ${r'#{'}${column.objectName}${r'}'}
                    </if>
                </#list>
            </where>
    </sql>

        <sql id="Set_Clause">
            <trim prefix=" " prefixOverrides=",">
                <#list table.columns as column>
                    <if test="${column.objectName} != null">
                        , ${column.name}  = ${r'#{'}${column.objectName}${r'}'}
                    </if>
                </#list>
            </trim>
        </sql>
    <!--/**
    * 分页获取数据列表
    */-->
    <select id="list${entity.className}" parameterType="${entity.fullName}" resultMap="${entity.className?uncap_first}ResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
        <include refid="Where_Clause"/>
    </select>
    <!--/**
    * 获取全部数据
    */-->
    <select id="all${entity.className}"  resultMap="${entity.className?uncap_first}ResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
        <include refid="Where_Clause"/>
    </select>
    <!--/**
    * 根据ID查找数据
    */-->
    <select id="get${entity.className}" parameterType="Long"  resultMap="${entity.className?uncap_first}ResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
        WHERE id= ${r'#{id}'}
    </select>
    <!--/**
    * 根据条件查找数据
    */-->
    <select id="find${entity.className}" parameterType="${entity.fullName}"  resultMap="${entity.className?uncap_first}ResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${table.name}
        <include refid="Where_Clause"/>
    </select>
    <!--
    /**
    * 添加数据
    */-->
    <insert id="add${entity.className}" parameterType="${entity.fullName}">
        INSERT INTO ${table.name}(
        <#list table.columns as column>
            ${column.name} <#if column_has_next>,</#if>
        </#list>
        )
        VALUES (
        <#list table.columns as column>
            <#if column.objectName=="rule" >
                ${r'#{'}${column.objectName}${r'}'} <#if column_has_next>,</#if>
                <#else >
                    ${r'#{'}${column.objectName}${r'}'} <#if column_has_next>,</#if>
            </#if>
        </#list>
        )
    </insert>
    <!--
        /**
        * 更新数据 不为空的值
        */
        -->
    <update id="update${entity.className}" parameterType="${entity.fullName}">
        UPDATE ${table.name}
        SET
        <include refid="Set_Clause"/>
        <include refid="Where_Clause"/>
    </update>
    <#--/**
    * 几条数据
    */-->
    <select id="count${entity.className}" parameterType="${entity.fullName}"  resultType="java.lang.Long">
        SELECT count(1)
        FROM ${table.name}
        <include refid="Where_Clause"/>
    </select>
    <#--/**
    * 统计 某个字段
    */-->
    <select id="sum${entity.className}" parameterType="${entity.fullName}"  resultType="java.math.BigDecimal">
        SELECT sum(1)
        FROM ${table.name}
        <include refid="Where_Clause"/>
    </select>
</mapper>
