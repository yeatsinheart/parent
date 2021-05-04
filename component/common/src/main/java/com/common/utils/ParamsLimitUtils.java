package com.common.utils;

import java.util.Map;

public class ParamsLimitUtils {
    // 接口只能针对一个参数做限制
    //
    public static boolean check(Map<String, String> params, String limits) {
        String[] limitArr = limits.split(",");
        for (String limit : limitArr) {
            String[] info = limit.split(":");
            String key = info[0];
            String condition = info[1];
            String value = info[2];
            if ("IN".equals(condition)) {
                String[] values = value.split(",");
                boolean contains = false;
                for (String v : values) {
                    if (v.equals(params.get(key))) {
                        return true;
                    }
                }
            } else if ("=".equals(condition)) {
                if (value.equals(params.get(key))) {
                    return true;
                }
            }
        }
        return false;
    }
}
