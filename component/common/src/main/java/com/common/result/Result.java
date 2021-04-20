package com.common.result;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public Result(ResultCode resultCode,String language) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage(language);
    }

    public Result(ResultCode resultCode, T data,String language) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage(language);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
