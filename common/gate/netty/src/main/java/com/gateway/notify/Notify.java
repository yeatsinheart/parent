package com.gateway.notify;

import com.base.constant.Language;
import com.base.result.Result;
import com.base.result.ResultGenerator;

public class Notify {
    public Result call() {
        return ResultGenerator.genFailResult(Language.中文.getCode());
    }
}
