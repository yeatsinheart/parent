package com.base.utils;

import org.apache.commons.lang3.StringUtils;


public class IpUtils {

    /**
     * 获取ip 前三段
     */
    public static String getSubIp(String ip) {
        int count = StringUtils.countMatches(ip, ".");
        if (count == 3) {
            ip = StringUtils.substring(ip, 0, StringUtils.ordinalIndexOf(ip, ".", 3) + 1); //获取ip前三段
        }
        return ip;
    }
}
