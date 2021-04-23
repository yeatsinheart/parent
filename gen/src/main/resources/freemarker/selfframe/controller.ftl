package ${controller.packageName};
import javax.validation.Valid;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.math.BigDecimal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.result.Result;
<#if lombok >
    import lombok.extern.slf4j.Slf4j;
</#if>

import ${dto.fullName};
import ${pto.fullName};
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
@RequestMapping("/${table.objectName}")
public class ${controller.className}   {
<#if lombok >
<#else >
    private static final Log log = LogFactory.getLog(${controller.className}.class);
</#if>
@DubboReference
private ${service.className} ${service.className?uncap_first};
/**
* 获取数据列表
*/
<#if swagger>
    @ApiOperation(value = "没有参数", notes = "没有参数")
</#if>
@PostMapping("selectList")
public Object list${entity.className}( @Valid @ModelAttribute ${dto.className} ${dto.className?uncap_first}){
Result
<List<${dto.className}>> result = ${service.className?uncap_first}.selectAll(null);
List<${dto.className}> data = result.getData();
return JSON.toJSONString(data);
}
}


