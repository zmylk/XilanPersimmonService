package com.xilan.common.advice;


import com.xilan.common.exception.LyException;
import com.xilan.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 因为不是SpringClound项目所以其他模块无法访问
 */
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handlerException(LyException e)
    {
        return ResponseEntity.status(e.getExceptionEnumm().getCode()).body(new ExceptionResult(e.getExceptionEnumm()));
    }
}
