package com.base.secret;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public static byte[] sha(String request, String algorithm) {
        request = request.trim();
        byte[] value;
        try {
            value = request.getBytes(StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            value = request.getBytes();
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return md.digest(value);
    }

    public static String sha256(String request) {
        return toHex(sha(request, "SHA-256"));
    }


    public static String sha512(String request) {
        return toHex(sha(request, "SHA-512"));

    }

    // 十六进制
    public static String toHex(byte[] input) {
        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

}
