package ${controller.packageName};
import javax.validation.Valid;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.math.BigDecimal;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;
import com.common.utils.JsonUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
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
* 新增数据
*/
@Operation(summary = "新增数据",description = "数据库中新增该记录",
responses = {
@ApiResponse(description = "返回的是json数据",
content = @Content(mediaType = "application/json",
schema = @Schema(implementation = String.class))),
@ApiResponse(responseCode = "400", description = "返回400时候错误的原因")},
security = @SecurityRequirement(name = "需要认证"))
@PostMapping("create")
public Object create${entity.className}( @RequestBody @Valid ${pto.className} ${pto.className?uncap_first}){
Result<${dto.className}> result = ${service.className?uncap_first}.create(${pto.className}.convert2DTO(${pto.className?uncap_first}));
${dto.className} data = result.getData();
return JsonUtil.toJsonStr(data);
}
/**
* 获取数据列表
*/
@PostMapping("selectList")
public Object list${entity.className}( @Valid @ModelAttribute ${dto.className} ${dto.className?uncap_first}){
Result
<List<${dto.className}>> result = ${service.className?uncap_first}.selectAll();
List<${dto.className}> data = result.getData();
return JsonUtil.toJsonStr(data);
}
}


