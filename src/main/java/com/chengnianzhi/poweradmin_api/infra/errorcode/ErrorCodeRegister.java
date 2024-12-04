package com.chengnianzhi.poweradmin_api.infra.errorcode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ErrorCodeRegister {
    public static void init() {
        register(SystemErrorCode.class, new CodeRange(10000, 20000));
    }

    static final Map<Class<? extends ErrorCode>, CodeRange> ERROR_CODE_RANGE_MAP = new ConcurrentHashMap<>();

    static void register(Class<? extends ErrorCode> c, CodeRange codeRange) {
        if (!c.isEnum()){
            throw new IllegalArgumentException("ErrorCode class must be enum");
        }
        if (ERROR_CODE_RANGE_MAP.containsKey(c)) {
            throw new IllegalArgumentException("ErrorCode class already registered");
        }
        ERROR_CODE_RANGE_MAP.put(ErrorCode.class, codeRange);
    }
}
