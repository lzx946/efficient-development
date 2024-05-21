package com.lzx.springbootinit.member.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzx.springbootinit.common.exception.BizException;
import com.lzx.springbootinit.common.result.ErrorCode;
import com.lzx.springbootinit.member.domain.Member;
import com.lzx.springbootinit.member.model.rq.MemberLoginRQ;
import com.lzx.springbootinit.member.security.MemberUserDetail;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@RequiredArgsConstructor
@Service
public class MemberAuthService {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    // 盐值
    private final String MEMBER_PASSWORD_SALT = "salt";

    /**
     * 用户注册
     *
     * @param account       账号
     * @param password      密码
     * @param checkPassword 确认密码
     * @return              用户ID
     */
    @Transactional
    public Long register(String account, String password, String checkPassword) {

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

            return member.getId();
        }

    }

    /**
     * 用户登录
     *
     * @param rq
     * @return
     */
    public Member login(MemberLoginRQ rq) {

        String password = DigestUtils.md5DigestAsHex((MEMBER_PASSWORD_SALT + rq.getPassword()).getBytes());

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(rq.getAccount(), password));

        MemberUserDetail memberUserDetail = (MemberUserDetail) authenticate.getDetails();
        return memberUserDetail.getMember();
    }

    /**
     * 查询当前登录用户
     *
     * @return
     */
    public Member getLoginMember() {

        MemberUserDetail details = (MemberUserDetail) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return details.getMember();
    }
}
