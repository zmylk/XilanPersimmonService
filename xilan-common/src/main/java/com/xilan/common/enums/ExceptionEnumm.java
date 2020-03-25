package com.xilan.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnumm {
    CODE_CANNOT_BE_NULL(400,"code值不可以是null"),
    SIGNATURE_CHECK_BE_FAIL(500,"签名校验失败"),
    LIKE_NOW_TEST_ERRO(400,"测试统异常返回")
            ;

    private int code;
    private String msg;

}
