package com.lzx.springbootinit.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用响应结果类，用于封装接口的返回结果
 *
 * @param <T>
 */
@AllArgsConstructor
@Data
public class Result<T> {

    private String code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return res(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> Result<T> error(String code, String message) {
        return res(code, message, null);
    }

    public static <T> Result<T> res(String code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
