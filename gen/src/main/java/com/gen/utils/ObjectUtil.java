package com.gen.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectUtil {
    static Map<String, List<String>> moduleTables = new HashMap<>();

    static {
        moduleTables.put("tenant", Arrays.asList(""));
    }
}
