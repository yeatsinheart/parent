package com.common.result;


import com.common.utils.JsonUtil;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
@Slf4j
public class Result<T> implements Serializable {
    @Parameter(description = "提示", example = "重复登陆", required = true)
    private final String message;
    @Parameter(description = "状态码", example = "1", required = true)
    private int code;
    @Parameter(description = "具体对象", example = "{}", required = true)
    private T data;

    public <U extends T> Result(ResultCode resultCode, String language) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage(language);
    }

    public <U extends T> Result(ResultCode resultCode, U data, String language) {
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

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonStr(this);
    }
}
