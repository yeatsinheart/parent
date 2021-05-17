package com.gateway.dubbo;

import com.base.result.ResultGenerator;
import com.base.utils.JsonUtil;
import com.gateway.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

    /**
     * 根据api查找具体的接口定义
     */
    public DubboRemoteService getApi(Integer api) {
        return new DubboRemoteService();
    }

    public Object invoke(Integer api, String group, String version, Map<String, Object> params, String language) {
        // 接口定义
        DubboRemoteService service = getApi(api);
        Auth.auth(params);

        // 接口参数定义
        String[] paramTypes = metadataCollector.getParamsTypes(service, group, version);

        //调用缓存
        ReferenceConfig<GenericService> apiCache = apiCallerCache.get(api, group, version);

        //请求参数
        params = JsonUtil.toMap("{\"data1\":[{}]}");
        List list = (List) params.get("data");
        Object[] arr = CollectionUtils.isEmpty(list) ? new Object[0] : list.toArray();
        try {

            //{data:[{}]}

            GenericService caller = apiCache.get();
            // 方法有，接口有，version指定调试类型，group指定调用的应用集群
            // 方法
            // new String[]{"com.xxx.Person"}  参数类型
            // Map<String, Object> 请求参数
            Object result = caller.$invoke(service.getMethodName(), paramTypes, arr);
            return JsonUtil.toJsonStr(result);
        } catch (RpcException e1) {
            if (e1.isTimeout()) {
                log.error("超时: \n{},\n{},\n", service, params);
            } else if (e1.isForbidden()) {
                log.error("isForbidden: \n{},\n{},\n", service, params);
            } else {
                log.error("RPC failed request :\n{}, \n{},\n{},\n", service, params, e1);
            }
            return ResultGenerator.genFailResult(language);
        } catch (Exception e1) {
            log.error("服务调用异常 : \n{},\n{},\n{},\n", service, params, e1);
            return ResultGenerator.genFailResult(language);
        }
    }


}
