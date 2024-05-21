package com.lzx.springbootinit.member.controller;

import com.lzx.springbootinit.common.result.Result;
import com.lzx.springbootinit.member.domain.Member;
import com.lzx.springbootinit.member.model.rq.MemberLoginRQ;
import com.lzx.springbootinit.member.model.rq.MemberRegisterRQ;
import com.lzx.springbootinit.member.model.rs.LoginMemberRS;
import com.lzx.springbootinit.member.service.MemberAuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    public Result<LoginMemberRS> login(@Validated @RequestBody MemberLoginRQ rq, HttpServletRequest request) {
        Member member = memberAuthService.login(rq, request);

        LoginMemberRS rs = new LoginMemberRS();
        BeanUtils.copyProperties(member, rs);

        return Result.success(rs);
    }
}
