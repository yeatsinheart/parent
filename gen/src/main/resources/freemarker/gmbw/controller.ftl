package ${controller.packageName};
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
<#if lombok >
    import lombok.extern.slf4j.Slf4j;
</#if>
<#if swagger >
import io.swagger.annotations.*;
</#if>
import ${entity.fullName};
import ${dto.fullName};
import ${service.fullName};
/**
* ${table.comment!}
* @author ${author}
* @since ${time}
*/
<#if lombok >
@Slf4j
</#if>
@RestController
@RequestMapping("/${entity.className}")
public class ${controller.className}   {
<#if lombok >
<#else >
    private static final Log log = LogFactory.getLog(${controller.className}.class);
</#if>
    @Autowired
    private ${service.className} ${service.className?uncap_first};
    /**
    * 获取数据列表
    */
<#if swagger>
    @ApiOperation(value = "没有参数", notes = "没有参数")
</#if>
    @PostMapping("bind${entity.className}.json")
    public Object list${entity.className}( @Valid @ModelAttribute ${dto.className} ${dto.className?uncap_first}){
        List<${entity.className}> ${entity.className?uncap_first}List = ${service.className?uncap_first}.list${entity.className}( ${dto.className?uncap_first});
        return ${entity.className?uncap_first}List;
    }


    /**
    * 获取全部数据
    */
<#if swagger>
    @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
    @ApiImplicitParams({
<#list table.columns as column>
        @ApiImplicitParam(name = "${column.objectName?uncap_first}", value = "${column.comment!}", required = ${column.notNull!}, dataType = "${column.javaTypeName} "),
</#list>
        @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
        @ApiImplicitParam(name = "pageIndex", value = "页数", required = true, dataType = "int")
    })
</#if>
    @PostMapping("/all${entity.className}")
    public Object all${entity.className}(){
        List<${entity.className}> ${entity.className?uncap_first}List = ${service.className?uncap_first}.all${entity.className}();
        return ${entity.className?uncap_first}List;
    }


    /**
    * 根据ID查找数据
    */
<#if swagger>
    @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
    @ApiImplicitParams({
<#list table.columns as column>
        @ApiImplicitParam(name = "${column.objectName?uncap_first}", value = "${column.comment!}", required = ${column.notNull!}, dataType = "${column.javaTypeName} "),
</#list>
        @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
        @ApiImplicitParam(name = "pageIndex", value = "页数", required = true, dataType = "int")
    })
</#if>
    @PostMapping("/get${entity.className}")
    public Object get${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        ${entity.className} ${entity.className?uncap_first}List = ${service.className?uncap_first}.get${entity.className}( ${dto.className?uncap_first});
        return ${entity.className?uncap_first}List;
    }


    /**
    * 根据条件查找数据
    */
<#if swagger>
    @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
    @ApiImplicitParams({
<#list table.columns as column>
        @ApiImplicitParam(name = "${column.objectName?uncap_first}", value = "${column.comment!}", required = ${column.notNull!}, dataType = "${column.javaTypeName} "),
</#list>
        @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
        @ApiImplicitParam(name = "pageIndex", value = "页数", required = true, dataType = "int")
    })
</#if>
    @PostMapping("/find${entity.className}")
    public Object find${entity.className}(${dto.className} ${dto.className?uncap_first}){
        List<${entity.className}> ${entity.className?uncap_first}List = ${service.className?uncap_first}.find${entity.className}(${dto.className?uncap_first});
        return ${entity.className?uncap_first}List;
    }


    /**
    * 添加数据
    */
<#if swagger>
    @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
    @ApiImplicitParams({
    <#list table.columns as column>
        @ApiImplicitParam(name = "${column.objectName?uncap_first}", value = "${column.comment!}", required = ${column.notNull!}, dataType = "${column.javaTypeName} "),
    </#list>
        @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
        @ApiImplicitParam(name = "pageIndex", value = "页数", required = true, dataType = "int")
    })
</#if>
    @PostMapping("/add${entity.className}")
    public Object add${entity.className}(@Valid @ModelAttribute  ${dto.className} ${dto.className?uncap_first}){
        int addNum  = ${service.className?uncap_first}.add${entity.className}( ${dto.className?uncap_first});
        return addNum;
    }


    /**
    * 更新数据
    */
<#if swagger>
    @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
    @ApiImplicitParams({
    <#list table.columns as column>
        @ApiImplicitParam(name = "${column.objectName?uncap_first}", value = "${column.comment!}", required = ${column.notNull!}, dataType = "${column.javaTypeName} "),
    </#list>
        @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
        @ApiImplicitParam(name = "pageIndex", value = "页数", required = true, dataType = "int")
    })
</#if>
    @PostMapping("/update${entity.className}")
    public Object update${entity.className}(@Valid @ModelAttribute  ${dto.className} ${dto.className?uncap_first}){
        int updateNum = ${service.className?uncap_first}.update${entity.className}(  ${dto.className?uncap_first});
        return updateNum;
    }

    /**
    * 几条数据
    */
<#if swagger>
    @ApiOperation(value = "几条数据", notes = "几条数据")
    @ApiImplicitParams({
    <#list table.columns as column>
        @ApiImplicitParam(name = "${column.objectName?uncap_first}", value = "${column.comment!}", required = ${column.notNull!}, dataType = "${column.javaTypeName} "),
    </#list>
    @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
    @ApiImplicitParam(name = "pageIndex", value = "页数", required = true, dataType = "int")
    })
</#if>
    public Long count${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        return ${service.className?uncap_first}.count${entity.className}(  ${dto.className?uncap_first});
    }

    /**
    * 统计 某个字段
    */
<#if swagger>
    @ApiOperation(value = "统计 某个字段", notes = "统计 某个字段")
    @ApiImplicitParams({
    <#list table.columns as column>
        @ApiImplicitParam(name = "${column.objectName?uncap_first}", value = "${column.comment!}", required = ${column.notNull!}, dataType = "${column.javaTypeName} "),
    </#list>
    @ApiImplicitParam(name = "pageSize", value = "每页展示数量", required = true, dataType = "int"),
    @ApiImplicitParam(name = "pageIndex", value = "页数", required = true, dataType = "int")
    })
</#if>
    public BigDecimal sum${entity.className}( ${dto.className} ${dto.className?uncap_first}){
        return ${service.className?uncap_first}.sum${entity.className}(  ${dto.className?uncap_first});
    }
}


