package com.common.secret;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.*;

public class MD5Util {
    public static String md5(String string) {
        // 16进制
        char[] hexDigits =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = string.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] updateBytes = messageDigest.digest();
            int len = updateBytes.length;
            char[] myChar = new char[len * 2];
            int k = 0;
            for (int i = 0; i < len; i++) {
                byte byte0 = updateBytes[i];
                myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
                myChar[k++] = hexDigits[byte0 & 0x0f];
            }
            return new String(myChar);
        } catch (Exception e) {
            return null;
        }
    }


}
