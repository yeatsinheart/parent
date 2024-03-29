package com.base.utils;

import com.base.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NacosUtil {
    // 获取配置
    public static String getService(String serviceName, String group, String namespaceId, String url) {
        String service = HttpUtils.get("http://" + url + "/nacos/v1/ns/service?serviceName=" + serviceName + "&groupName=" + group + "&namespaceId=" + namespaceId + "", null);
        if ("config data not exist\n".equals(service)) {
            return null;
        }
        return service;
    }

    // 获取配置
    public static String getConfig(String dataId, String group, String namespaceId, String url) {
        String config = HttpUtils.get("http://" + url + "/nacos/v1/cs/configs?dataId=" + dataId + "&group=" + group + "&tenant=" + namespaceId + "", null);
        if ("config data not exist\n".equals(config)) {
            return null;
        }
        return config;
    }

    public static boolean putConfig(String content, String type, String dataId, String group, String namespaceId, String url) {
        Map<String, Object> params = new HashMap<>();
        params.put("tenant", namespaceId);
        params.put("dataId", dataId);
        params.put("group", group);
        params.put("content", urlencode(content));
        params.put("type", type);
        String putted = HttpUtils.postByForm("http://" + url + "/nacos/v1/cs/configs" + "", params, null);
        return "true".equals(putted);
    }

    public static String namespace(String url, String namespaceId) {
        HttpUtils.get("http://" + url + "/nacos/v1/console/namespaces", null);
        Map<String, Object> params = new HashMap<>();
        //命名空间ID
        params.put("customNamespaceId", namespaceId);
        //命名空间名
        params.put("namespaceName", namespaceId);
        String putted = HttpUtils.postByForm("http://" + url + "/nacos/v1/console/namespaces" + "", params, null);
        System.out.println(putted);
        return putted;
    }


    public static String urlencode(String args) {
        try {
            return URLEncoder.encode(args, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
