/*
 * Copyright (C) 2020  即时通讯网(52im.net) & Jack Jiang.
 * The MobileIMSDK v5.x Project.
 * All rights reserved.
 *
 * > Github地址：https://github.com/JackJiang2011/MobileIMSDK
 * > 文档地址：  http://www.52im.net/forum-89-1.html
 * > 技术社区：  http://www.52im.net/
 * > 技术交流群：320837163 (http://www.52im.net/topic-qqgroup.html)
 * > 作者公众号：“【即时通讯技术圈】”，欢迎关注！
 * > 联系作者：  http://www.52im.net/thread-2792-1-1.html
 *
 * "即时通讯网(52im.net) - 即时通讯开发者社区!" 推荐开源工程。
 *
 * CharsetHelper.java at 2020-8-22 16:00:59, code by Jack Jiang.
 */
package com.base.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Charset {

    public static String getString(byte[] b, int len) {
        try {
            return new String(b, 0, len, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return new String(b, 0, len);
        }
    }

    public static String getString(byte[] b, int start, int len) {
        try {
            return new String(b, start, len, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return new String(b, start, len);
        }
    }

    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                return str.getBytes();
            }
        } else
            return new byte[0];
    }
}
