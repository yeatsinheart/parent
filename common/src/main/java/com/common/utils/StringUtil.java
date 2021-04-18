package com.common.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    public static String firstUpper(String str){
        if(StringUtils.isEmpty(str)){
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
