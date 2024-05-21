package com.lzx.springbootinit.member.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzx.springbootinit.member.domain.Member;
import com.lzx.springbootinit.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberService.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getAccount, username));
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return new MemberUserDetail(member);
    }
}
