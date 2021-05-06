package com.common.utils;


public class I18nUtilTest {


    public static void main(String[] args) {
        System.out.println(I18nUtil.getString("common", "test", "en-US"));
        System.out.println(I18nUtil.getString("common", "test", "zh-CN"));
        System.out.println(I18nUtil.getString("common", "test", "zh-TW"));
        System.out.println(I18nUtil.getString("common", "test", "vi-VN"));
       /* System.out.println(I18nUtil.getMultString("admin_name", "admin_name_full"));
        System.out.println(I18nUtil.getMultString());*/
    }
}
