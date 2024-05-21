package com.lzx.springbootinit.common.exception;

import com.lzx.springbootinit.common.result.ErrorCode;
import com.lzx.springbootinit.common.result.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Result<Void> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return Result.error(ErrorCode.PARAM_ERROR.getCode(), String.format("%s[%s]参数必传", e.getParameterName(), e.getParameterType()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return CollectionUtils.isEmpty(bindingResult.getFieldErrors()) ? Result.error(ErrorCode.PARAM_ERROR.getCode(), "参数校验错误") : Result.error(ErrorCode.PARAM_ERROR.getCode(), bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Result<?> handleConstraintViolationException(ConstraintViolationException ex) {
        return StringUtils.hasText(ex.getMessage()) ? Result.error(ErrorCode.PARAM_ERROR.getCode(), ex.getMessage()) : Result.error(ErrorCode.PARAM_ERROR.getCode(), "参数校验不通过");
    }

    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        log.error("Exception", e);
        return Result.error(ErrorCode.SYSTEM_ERROR);
    }
}
