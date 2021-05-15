package com.gateway.dubbo;

import com.common.result.ResultGenerator;
import com.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DubboInvoke {
    @Resource
    private MetadataCollector metadataCollector;
    @Resource
    private ApiCallerCache apiCallerCache;

    public Object invoke(Integer api_id, String group, String version, String param, String language) {
        String module = null;
        String interfaceName = null;
        String methodName = null;
        try {

            ReferenceConfig<GenericService> apiCache = apiCallerCache.get(interfaceName, group, version);
            //{data:[{}]}
            Map<String, List> data = JsonUtil.toMap(param, List.class);
            Object[] arr = data.get("data").toArray();
            String[] paramTypes = metadataCollector.getParamsTypes(module, interfaceName, group, version, methodName, arr.length);
            GenericService caller = apiCache.get();
            Object result = caller.$invoke(methodName, paramTypes, arr);
            return JsonUtil.toJsonStr(result);
        } catch (RpcException e1) {
            if (e1.isTimeout()) {
                log.error("超时: \n{},\n{},\n{}", interfaceName, methodName, param);
            } else if (e1.isForbidden()) {
                log.error("isForbidden: \n{},\n{},\n{}", interfaceName, methodName, param);
            } else {
                log.error("RPC failed request :\n{}, \n{},\n{},\n{}", interfaceName, methodName, param, e1);
            }
            return ResultGenerator.genFailResult(language);
        } catch (Exception e1) {
            log.error("服务调用异常 : \n{},\n{},\n{},\n{}", interfaceName, methodName, param, e1);
            return ResultGenerator.genFailResult(language);
        }
    }


}
