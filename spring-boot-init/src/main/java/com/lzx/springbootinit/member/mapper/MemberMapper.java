package com.lzx.springbootinit.member.mapper;

import com.lzx.springbootinit.member.domain.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author liuzongxin
* @description 针对表【t_member(用户)】的数据库操作Mapper
* @createDate 2024-05-12 13:49:53
* @Entity generator.domain.Member
*/
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

}




