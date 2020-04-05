package com.xilan.begin.advice;


import com.xilan.common.exception.LyException;
import com.xilan.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handlerException(LyException e)
    {
        return ResponseEntity.status(e.getExceptionEnumm().getCode()).body(new ExceptionResult(e.getExceptionEnumm()));
    }
}
