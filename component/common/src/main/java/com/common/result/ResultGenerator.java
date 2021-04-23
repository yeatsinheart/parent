package com.common.result;

/**
 * 响应结果生成工具
 */
public class ResultGenerator<T> {

    public static Result genSuccessResult(String language) {
        return new Result(ResultCode.SUCCESS,language);
    }
    public static Result genSuccessResult(Object data,String language) {
        return new Result(ResultCode.SUCCESS,data,language);
    }
    public static PageResult genPageResult(long size,long current,long total,long pages,Object data,String language) {
        return new PageResult(size,current,total,pages,data,language);
    }
    public static Result genFailResult(String language) {
        return new Result(ResultCode.FAIL,language);
    }
    public static Result genResult(ResultCode resultCode,String language) {
        return new Result(resultCode,language);
    }
}
