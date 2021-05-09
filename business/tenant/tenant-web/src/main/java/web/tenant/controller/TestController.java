package web.tenant.controller;

import api.tenant.services.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.ui.Model;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.ResponseBody;
@Tag(name = "TestController")
@Controller
public class TestController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @DubboReference
    TestService testService;

    @GetMapping("/test")
     @ResponseBody
    public String test(String i) {
        return testService.test(i).getData();
    }
    @Operation(summary = "测试的接口",
            description = "描述的文字",
            responses = {
                    @ApiResponse(description = "返回的是页面",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")},
            security = @SecurityRequirement(name = "需要认证"))
    @GetMapping("/hello")
    public Mono<String> hello(final Model model) {
        model.addAttribute("name", "泥瓦匠");
        model.addAttribute("city", "浙江温岭");
        String path = "hello";
        return Mono.create(monoSink -> monoSink.success(path));
    }
}
