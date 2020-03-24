package com.xilan.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnumm {
    PRICE_CANNOT_BE_NULL(400,"几个不能为空！"),
    LIKE_NOW_TEST_ERRO(400,"测试统异常返回")
            ;

    private int code;
    private String msg;

}
