package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class EncodingTool {
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
