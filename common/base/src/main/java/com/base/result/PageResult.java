package com.base.result;


import com.base.utils.JsonUtil;
import lombok.SneakyThrows;

import java.io.Serializable;

/**
 * 统一分页API响应结果封装
 */
public class PageResult<T> implements Serializable {
    private int code;
    private T data;
    private final long total;
    private final long size;
    private final long current;
    private final long pages;

    public PageResult(long size, long current, long total, long pages, T data) {
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
