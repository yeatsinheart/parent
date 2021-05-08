package service.cms.services.impls;

import api.cms.services.TestService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.common.constant.Language;
import com.common.result.Result;
import com.common.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
@Slf4j
@DubboService
public class TestServiceImpl implements TestService {
    @NacosValue(value = "${testvalue}",autoRefreshed = true)
    private String nacosvalue;

    @Override
    public Result<String> test(String test) {
        return ResultGenerator.genSuccessResult("当前输入"+test+"，nacos中的值是"+nacosvalue,Language.中文.getCode());
    }
}
