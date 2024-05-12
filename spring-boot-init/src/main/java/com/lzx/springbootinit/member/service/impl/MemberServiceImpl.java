package com.lzx.springbootinit.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzx.springbootinit.member.domain.Member;
import com.lzx.springbootinit.member.mapper.MemberMapper;
import com.lzx.springbootinit.member.service.MemberService;
import org.springframework.stereotype.Service;

/**
* @author liuzongxin
* @description 针对表【t_member(用户)】的数据库操作Service实现
* @createDate 2024-05-12 13:49:53
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService {

}




