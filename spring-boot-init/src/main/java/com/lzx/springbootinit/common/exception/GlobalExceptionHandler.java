package com.lzx.springbootinit.common.exception;

import com.lzx.springbootinit.common.result.ErrorCode;
import com.lzx.springbootinit.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author Lzx
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public Result<?> bizExceptionHandler(BizException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        log.error("Exception", e);
        return Result.error(ErrorCode.SYSTEM_ERROR);
    }
}
