package com.gen.utils;

import com.common.constant.Language;
import com.common.result.Result;
import com.common.result.ResultCode;
import com.common.utils.JsonUtil;

public class ObjectUtil {
    public static void main(String[] args) {
        Result<Integer> t = new Result<Integer>(ResultCode.FAIL, 404, Language.中文.getCode());
        Class clazz = t.getClass();
        System.out.println(clazz);

        System.out.println(JsonUtil.toJsonStr(clazz));
    }
}
