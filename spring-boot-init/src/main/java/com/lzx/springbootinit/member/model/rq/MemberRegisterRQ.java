package com.lzx.springbootinit.member.model.rq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户注册请求参数
 */
@Data
public class MemberRegisterRQ {

    /**
     * 账户
     */
    @NotBlank(message = "账号不能为空")
    @Size(min = 4, max = 128, message = "账号长度应在4～128范围内")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 128, message = "密码长度应在8～128范围内")
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    @Size(min = 8, max = 128, message = "确认密码长度应在8～128范围内")
    private String checkPassword;
}
