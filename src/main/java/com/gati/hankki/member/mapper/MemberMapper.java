package com.gati.hankki.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gati.hankki.member.model.Member;

@Mapper
public interface MemberMapper {
    
	Member findByMemberId(String memberId);

}



