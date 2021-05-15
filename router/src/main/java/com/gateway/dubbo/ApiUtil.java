//package com.gateway.dubbo;
//
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ConcurrentHashMap;
//
//@Service
//public class ApiUtil {
//    private ConcurrentHashMap<Integer, GlobalApiResponseDto> interfaceCache = new ConcurrentHashMap();
//    @DubboReference
//    private GlobalApiService globalApiService;
//
//    /*缓存获取接口信息*/
//    public GlobalApiResponseDto getInterface(Integer apiId) {
//        return interfaceCache.getOrDefault(apiId, getInterfaceFromDB(apiId));
//    }
//
//    /*数据库获取接口信息
//    @PostConstruct
//    public void cache() {
//        Result<List<GlobalApiResponseDto>> result = globalApiService.getAllGlobalApi();
//        List<GlobalApiResponseDto> apis = result.getData();
//        apis.forEach(api -> {
//            interfaceCache.put(api.getId(), api);
//        });
//    }*/
//
//    public GlobalApiResponseDto getInterfaceFromDB(Integer apiId) {
//        updateInterfaceCache();
//        return interfaceCache.getOrDefault(apiId, null);
//    }
//
//    /*更新缓存接口信息*/
//    public synchronized void updateInterfaceCache() {
//        ConcurrentHashMap<Integer, GlobalApiResponseDto> tmp= new ConcurrentHashMap();
//        interfaceCache.clear();
//        try {
//            Result<List<GlobalApiResponseDto>> result = globalApiService.getAllGlobalApi();
//            List<GlobalApiResponseDto> apis = result.getData();
//            apis.forEach(api -> {
//                tmp.put(api.getId(), api);
//            });
//            interfaceCache=tmp;
//        } catch (Exception e) {
//
//        }
//    }
//}
