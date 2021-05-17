package com.base.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class MapUtil {

    /**
     * ASCII排序
     *
     * @param parameters
     * @return get请求后缀
     */
    public static String orderByAscii(Map<String, Object> parameters) {
        List<Map.Entry<String, Object>> infoIds = new ArrayList<>(parameters.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        Collections.sort(infoIds, Comparator.comparing(o -> (o.getKey())));
        StringBuffer sign = new StringBuffer();
        for (Map.Entry<String, Object> item : infoIds) {
            String k = item.getKey();
            if (!StringUtils.isEmpty(item.getKey())) {
                Object v = item.getValue();
                if (null != v && !ObjectUtils.isEmpty(v)) {
                    sign.append(k + "=" + v + "&");
                }
            }
        }
        return sign.deleteCharAt(sign.length() - 1).toString();
    }

    /**
     * 按照LinkdHashMap排序
     *
     * @param parameters
     * @return
     */
    public static String orderByLinkdHash(Map<String, String> parameters) {
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(parameters.entrySet());
        StringBuffer sign = new StringBuffer();
        for (Map.Entry<String, String> item : infoIds) {
            String k = item.getKey();
            if (!StringUtils.isEmpty(item.getKey())) {
                Object v = item.getValue();
                if (null != v && !ObjectUtils.isEmpty(v)) {
                    sign.append(k + "=" + v + "&");
                }
            }
        }
        return sign.deleteCharAt(sign.length() - 1).toString();
    }
}
