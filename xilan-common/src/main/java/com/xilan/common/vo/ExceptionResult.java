package com.xilan.common.vo;


import com.xilan.common.enums.ExceptionEnumm;
import lombok.Data;

@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnumm en) {
        status = en.getCode();
        message = en.getMsg();
        timestamp = System.currentTimeMillis();
    }
}
