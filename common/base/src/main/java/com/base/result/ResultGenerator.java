package com.base.result;

/**
 * 响应结果生成工具
 */
public class ResultGenerator<T> {

    public static Result genSuccessResult() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result genSuccessResult(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static PageResult genPageResult(long size, long current, long total, long pages, Object data) {
        return new PageResult(size, current, total, pages, data);
    }

    public static Result genFailResult() {
        return new Result(ResultCode.FAIL);
    }

    public static Result genResult(ResultCode resultCode) {
        return new Result(resultCode);
    }
}
