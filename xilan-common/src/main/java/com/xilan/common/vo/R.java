package com.xilan.common.vo;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
    
    public static R ok(Object result) {
        R r = new R();
        r.put("result", result);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
