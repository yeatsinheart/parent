package com.base.secret;


import java.util.Base64;

public class Base64Util {
    //base64 解码
    public static String decode(byte[] bytes) {
        return new String(Base64.getDecoder().decode(bytes));
    }

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }

    public static String img(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("data:image/jpeg;base64,")
                .append(encode(bytes));
        return buffer.toString();
    }

    public static void main(String[] args) {
        String string = "测试Base64编码";
        //编码
        String encode = encode(string.getBytes());
        System.out.println(string + "\t编码后的字符串为：" + encode);
        //解码
        String decode = decode(encode.getBytes());
        System.out.println(encode + "\t字符串解码后为：" + decode);
    }
}
