package com.db.result.dailywages;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static <T> WebResultBase<T> genSuccessResult() {
        WebResultBase<T> result = new WebResultBase<>();
        result.setMsg(WebResultBase.StatusMessage.OK);
        result.setResult(WebResultBase.StatusCode.SUCCESS);
        return result;
    }

    public static <T> WebResultBase<T> genSuccessResult(String msg) {
        WebResultBase<T> result = new WebResultBase<>();
        result.setMsg(msg);
        result.setResult(WebResultBase.StatusCode.SUCCESS);
        return result;
    }

    public static <T> WebResultBase<T> genSuccessResult(T root) {
        WebResultBase<T> result = genSuccessResult();
        result.setRoot(root);
        return result;
    }


    public static <T> WebResultBase<T> genFailResult(String message) {
        return genFailResult(null, message);
    }

    public static <T> WebResultBase<T> genFailResult(T root, String message) {
        WebResultBase<T> result = genFailResult();
        result.setRoot(root);
        result.setMsg(message);
        return result;
    }

    public static <T> WebResultBase<T> genFailResult() {
        WebResultBase<T> result = new WebResultBase<>();
        result.setMsg(WebResultBase.StatusMessage.FAIL);
        result.setResult(WebResultBase.StatusCode.FAIL);
        return result;
    }

    public static <T> WebResultBase<T> genPermissionErrorResult() {
        WebResultBase<T> result = new WebResultBase<>();
        result.setMsg("您的登录已失效，请重新登录");
        result.setResult(WebResultBase.StatusCode.PERMISSION_ERROR);
        return result;
    }

    public static <T> WebResultBase<T> genPermissionErrorResult(HttpServletResponse response) {
        WebResultBase<T> result = new WebResultBase<>();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        result.setMsg("您的登录已失效，请重新登录");
        result.setResult(WebResultBase.StatusCode.PERMISSION_ERROR);
        return result;
    }

    public static <T> WebResultBase<T> genFailResult(List<String> errorToMessages) {
        return genFailResult(StringUtils.join(errorToMessages, ","));
    }

    public static String parserJSONPFormat(String callback, WebResultBase<Object> resultBase) {
        return callback + "(" + JSON.toJSONString(resultBase) + ")";
    }

}
