package com.common.constant;

/**
 * 页面跳转类型
 */
public class RedirectionType {
    /**
     * 1：网页通过IFRAME，app直接唤起webview
     */
    public static final int IFRAME = 1;
    /**
     * 2：网页直接跳转，app直接唤起webview
     */
    public static final int FORWARD = 2;
    /**
     * 3：网页新窗口，app直接唤起webview
     */
    public static final int NEWWINDOW = 3;
    /**
     * 4：PC IFRAME H5新窗口，app直接唤起webview
     */
    public static final int H5NEWWINDOW = 4;
}
