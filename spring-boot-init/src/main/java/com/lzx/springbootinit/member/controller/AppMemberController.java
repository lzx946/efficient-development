package com.lzx.springbootinit.member.controller;

import com.lzx.springbootinit.common.result.Result;
import com.lzx.springbootinit.member.domain.Member;
import com.lzx.springbootinit.member.model.rq.MemberLoginRQ;
import com.lzx.springbootinit.member.model.rq.MemberRegisterRQ;
import com.lzx.springbootinit.member.model.rs.LoginMemberRS;
import com.lzx.springbootinit.member.service.MemberAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api-member/member")
public class AppMemberController {

    private final MemberAuthService memberAuthService;

    /**
     * 用户注册
     *
     * @param rq
     * @return
     */
    @PostMapping("/register")
    public Result<Long> register(@Validated @RequestBody MemberRegisterRQ rq) {
        return Result.success(memberAuthService.register(rq.getAccount(), rq.getPassword(), rq.getCheckPassword()));
    }

    /**
     * 用户登录
     *
     * @param rq
     * @return
     */
    @PostMapping("/login")
    public Result<LoginMemberRS> login(@Validated @RequestBody MemberLoginRQ rq) {
        Member member = memberAuthService.login(rq);

        LoginMemberRS rs = new LoginMemberRS();
        BeanUtils.copyProperties(member, rs);

        return Result.success(rs);
    }

    /**
     * 查询当前登录用户
     *
     * @return
     */
    @GetMapping("/get-login-member")
    public Result<LoginMemberRS> getLoginMember() {
        Member member = memberAuthService.getLoginMember();

        LoginMemberRS rs = new LoginMemberRS();
        BeanUtils.copyProperties(member, rs);

        return Result.success(rs);
    }
}
