package com.gateway.notify;

import com.common.constant.Language;
import com.common.result.Result;
import com.common.result.ResultGenerator;

public class Notify {
    public Result call() {
        return ResultGenerator.genFailResult(Language.中文.getCode());
    }
}
