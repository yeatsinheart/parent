package com.gateway.dubbo;

import com.base.result.ResultGenerator;
import com.base.utils.JsonUtil;
import com.gateway.dubbo.caller.CallerCache;
import com.gateway.dubbo.meta.MetadataCollector;
import com.gateway.dubbo.caller.DubboRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class DubboInvoke {
    @Resource
    private MetadataCollector metadataCollector;
    @Resource
    private CallerCache callerCache;


    public Object invoke(DubboRemoteService service, DubboRequest request) {
        String[] paramTypes = metadataCollector.getParamsTypes(service);
        ReferenceConfig<GenericService> invokerCache = callerCache.get(service);
        if (null == request.getData()) {
            request.setData(new Object[0]);
        }
        try {
            GenericService invoker = invokerCache.get();
            Object result = invoker.$invoke(service.getMethodName(), paramTypes, request.getData());
            return JsonUtil.toJsonStr(result);
        } catch (RpcException e1) {
            if (e1.isTimeout()) {
                log.error("超时: \n{},\n{},\n", service, request);
            } else if (e1.isForbidden()) {
                log.error("isForbidden: \n{},\n{},\n", service, request);
            } else {
                log.error("RPC failed request :\n{}, \n{},\n{},\n", service, request, e1);
            }
            return ResultGenerator.genFailResult();
        } catch (Exception e1) {
            log.error("服务调用异常 : \n{},\n{},\n{},\n", service, request, e1);
            return ResultGenerator.genFailResult();
        }
    }


}
