package com.base.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class EncodingTool {
    public static String encodeStr(String str) {
        try {
            return new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
