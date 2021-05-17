package com.gen.utils;

import com.base.constant.Language;
import com.base.result.Result;
import com.base.result.ResultCode;
import com.base.utils.JsonUtil;

public class ObjectUtil {
    public static void main(String[] args) {
        Result<Integer> t = new Result<Integer>(ResultCode.FAIL, 404, Language.中文.getCode());
        Class clazz = t.getClass();
        System.out.println(clazz);

        System.out.println(JsonUtil.toJsonStr(clazz));
    }
}
