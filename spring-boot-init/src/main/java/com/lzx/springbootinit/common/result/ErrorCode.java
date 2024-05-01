package com.lzx.springbootinit.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类
 */
@AllArgsConstructor
@Getter
public enum ErrorCode {

    SUCCESS("0000", "请求成功"),
    PARAM_ERROR("0400", "参数错误"),
    NOT_LOGIN_ERROR("0401", "未登录"),
    NO_AUTH_ERROR("0402", "无权限"),
    SYSTEM_ERROR("0500", "系统内部错误"),
    ;

    private final String code;
    private final String message;
}
