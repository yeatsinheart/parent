package com.gateway.router;

import com.common.dto.BaseRequest;

public interface Router {
    String handle(BaseRequest reqest);
}
