package com.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class XssUtil {
    /**
     * 如果找到非法字符则返回true,如果没找到则返回false
     *
     * @param value
     * @return
     */
    public static boolean stripXSS(String value) {
        boolean result = false;
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        if (value.contains("<") || value.contains(">") || value.contains("&") || value.contains("'") || value.contains("=") || value.contains("*")) {
            return true;
        }
        // Avoid null characters
        value = value.replaceAll(" ", "");
        value = value.replaceAll("\\\\", "");

        // Avoid anything between script tags
        Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
                Pattern.CASE_INSENSITIVE);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        // //如果找到则为true
        if (result)
            return result;

        // Avoid anything in a src='...' type of expression
        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        // Remove any lonesome </script> tag
        scriptPattern = Pattern.compile("</script>",
                Pattern.CASE_INSENSITIVE);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        // Remove any lonesome <script ...> tag
        scriptPattern = Pattern.compile("<script(.*?)>",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        // Avoid eval(...) expressions
        scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        // Avoid expression(...) expressions
        scriptPattern = Pattern.compile("expression\\((.*?)\\)",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        scriptPattern = Pattern.compile("vbscript:",
                Pattern.CASE_INSENSITIVE);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        // Avoid onload= expressions
        scriptPattern = Pattern.compile("onload(.*?)=",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                        | Pattern.DOTALL);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;

        // Avoid alert:... expressions
        scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
        result = scriptPattern.matcher(value).find();// .replaceAll("");
        if (result)
            return result;
        return result;
    }
}
