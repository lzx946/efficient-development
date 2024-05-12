package com.lzx.springbootinit.member.controller;

import com.lzx.springbootinit.common.result.Result;
import com.lzx.springbootinit.member.domain.Member;
import com.lzx.springbootinit.member.model.rq.MemberRegisterRQ;
import com.lzx.springbootinit.member.service.MemberAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
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
    public Result<Member> register(@Validated @RequestBody MemberRegisterRQ rq) {
        return Result.success(memberAuthService.register(rq.getAccount(), rq.getPassword(), rq.getCheckPassword()));
    }
}
