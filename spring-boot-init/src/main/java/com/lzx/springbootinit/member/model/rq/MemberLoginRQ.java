package com.lzx.springbootinit.member.model.rq;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户登录请求参数
 */
@Data
public class MemberLoginRQ {

    /**
     * 账户
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

}
