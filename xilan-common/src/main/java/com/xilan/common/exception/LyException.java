package com.xilan.common.exception;


import com.xilan.common.enums.ExceptionEnumm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LyException extends RuntimeException {

    private ExceptionEnumm exceptionEnumm;

}
