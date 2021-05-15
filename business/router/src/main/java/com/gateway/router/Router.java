package com.gateway.router;


import java.util.Map;

public interface Router {
    String handle(Map<String, String> params);
}
