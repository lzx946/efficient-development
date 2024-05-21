package com.lzx.springbootinit.member.model.rs;

import lombok.Data;

/**
 * 登录用户响应参数
 */
@Data
public class LoginMemberRS {

    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别，0-女，1-男，2-保密
     */
    private String gender;

    /**
     * 用户简介
     */
    private String profile;

}
