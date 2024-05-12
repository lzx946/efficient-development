package com.lzx.springbootinit.member.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzx.springbootinit.common.exception.BizException;
import com.lzx.springbootinit.common.result.ErrorCode;
import com.lzx.springbootinit.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@RequiredArgsConstructor
@Service
public class MemberAuthService {

    private final MemberService memberService;

    // 盐值
    private final String MEMBER_PASSWORD_SALT = "salt";

    /**
     * 用户注册
     *
     * @param account       账号
     * @param password      密码
     * @param checkPassword 确认密码
     * @return
     */
    public Member register(String account, String password, String checkPassword) {

        // 校验密码和确认密码是否相等
        if (!password.equals(checkPassword)) {
            throw new BizException(ErrorCode.PARAM_ERROR, "两次输入密码不一致");
        }

        synchronized (account.intern()) {

            // 校验账号是否重复
            long count = memberService.count(Wrappers.<Member>lambdaQuery().eq(Member::getAccount, account));
            if (count > 0) {
                throw new BizException("账号已存在");
            }

            Member member = new Member();
            member.setAccount(account);
            member.setPassword(DigestUtils.md5DigestAsHex((MEMBER_PASSWORD_SALT + password).getBytes()));

            boolean saved = memberService.save(member);
            if (!saved) {
                throw new BizException("注册失败，数据库异常");
            }

            return member;
        }

    }
}
