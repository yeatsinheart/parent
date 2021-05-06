package com.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

//服务器时区->目标时区,一切时间都要以UTC为准
public class LocalDateTimeUtil {
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前UTC时间
     */
    public static LocalDateTime utc() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    /**
     * 获取当前UTC+8时间
     */
    public static LocalDateTime utc8() {
        return LocalDateTime.now(ZoneOffset.of("+8"));
    }

    /**
     * 获取当前UTC时间毫秒时间戳   13位
     */
    public static Long timestamp13() {
        return utc().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    /**
     * 获取当前UTC+8时间秒数时间戳
     */
    public static Long timestamp10() {
        return utc().toEpochSecond(ZoneOffset.UTC);
    }

    //将服务器时间根据服务器时区输出为字符串
    public static String time2Str(LocalDateTime localDateTime, String patten) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
        return dateTimeFormatter.format(localDateTime);
    }

    public static String timestamp13Str(Long timestamp13, String patten) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp13).atZone(ZoneOffset.UTC).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
        return dateTimeFormatter.format(localDateTime);
    }

    public static String timestamp10Str(Long timestamp10, String patten) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp10, 0, ZoneOffset.ofHours(8));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
        return dateTimeFormatter.format(localDateTime);
    }

    // 注意：字符串代表的时区是什么时区,那么获取出来的时间就是 什么时区，所以不接受请求带时区进来。请求的时间只能是UTC时区时间
    public static LocalDateTime str2Time(String localDateTime, String patten) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patten);
        return LocalDateTime.parse(localDateTime, dateTimeFormatter);
    }

    public static void main(String[] args) {
        // 针对的是当前服务器时间的时间戳，格式化为固定的一个时间
        System.out.println("当前时间  在 某个时区(地区)  显示为");
        System.out.println(utc());
        System.out.println(utc8());
        System.out.println(timestamp10() + ":" + timestamp10Str(timestamp10(), yyyyMMddHHmmssFormat) + "：" + str2Time(timestamp10Str(timestamp10(), yyyyMMddHHmmssFormat), yyyyMMddHHmmssFormat));
        System.out.println(timestamp13() + ":" + timestamp13Str(timestamp13(), yyyyMMddHHmmssFormat) + "：" + str2Time(timestamp13Str(timestamp13(), yyyyMMddHHmmssFormat), yyyyMMddHHmmssFormat));
    }
}
