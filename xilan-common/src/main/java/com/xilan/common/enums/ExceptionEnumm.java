package com.xilan.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnumm {
    CODE_CANNOT_BE_NULL(400,"code值不可以是null"),
    OPENID_CANNOT_BE_NULL(400,"openId值不可以是null"),
    SIGNATURE_CHECK_BE_FAIL(500,"签名校验失败"),
    LIKE_NOW_TEST_ERRO(400,"测试统异常返回"),
    CANNOT_BE_NULL(400,"参数不可以为空!"),
    NOT_CANT_GET_SESSIONID_OPENID(500,"微信小程序接口返回错误码，无法获取sessionId和openId"),
    NOT_CANT_REQUEST_AGAIN(400,"已经发送过开始请求"),
    NOT_RECORDING(400,"这个id没有查询到任何事项记录"),
    NOT_RECORDING_BEGIN(400,"没有开始记录")
            ;

    private int code;
    private String msg;

}
