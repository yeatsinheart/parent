package com.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

//服务器时区->目标时区
public class LocalDateTimeUtil {
    /**
     * 获取当前UTC时间
     */
    public static LocalDateTime getNowUTC() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    /**
     * 获取当前UTC+8时间
     */
    public static LocalDateTime getNowUTC8() {
        return LocalDateTime.now(ZoneOffset.of("+8"));
    }

    /**
     * 获取当前UTC时间秒数时间戳
     */
    public static Long getNowUTCSsTimestamp() {
        return getNowUTC().toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * 获取当前UTC时间毫秒时间戳
     */
    public static Long getNowMsTimestamp() {
        return getNowUTC().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    /**
     * 获取当前UTC+8时间秒数时间戳
     */
    public static Long getNowSsTimestamp() {
        return getNowUTC8().toEpochSecond(ZoneOffset.of("+8"));
    }


    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static String yyyyMMddHHmmssFormat = "yyyy-MM-dd HH:mm:ss";

    //将服务器时间根据服务器时区输出为字符串
    public static String time2Str(LocalDateTime localDateTime, String patten) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
        return dateTimeFormatter.format(localDateTime);
    }

    //将字符串根据服务器时区格式化为服务器时间
    public static LocalDateTime str2Time(String localDateTime, String patten) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
        return LocalDateTime.parse(localDateTime, dateTimeFormatter);
    }

    public static void main(String[] args) {
        // 针对的是当前服务器时间的时间戳，格式化为固定的一个时间
        System.out.println(getNowUTC());
        System.out.println(getNowUTC8());
        System.out.println(getNowMsTimestamp());
        System.out.println(getNowMsTimestamp());
    }
}
