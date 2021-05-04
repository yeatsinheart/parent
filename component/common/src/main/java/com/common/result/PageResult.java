package com.common.result;


import com.common.utils.JsonUtil;
import lombok.SneakyThrows;

import java.io.Serializable;

/**
 * 统一分页API响应结果封装
 */
public class PageResult<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    private long total;
    private long size;
    private long current;
    private long pages;

    public PageResult(long size, long current, long total, long pages, T data, String language) {
        this.code = ResultCode.SUCCESS.getCode();
        this.size = size;
        this.current = current;
        this.total = total;
        this.pages = pages;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public PageResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public PageResult setData(T data) {
        this.data = data;
        return this;
    }


    @SneakyThrows
    @Override
    public String toString() {
        return JsonUtil.toJsonStr(this);
    }
}
