package com.gati.hankki.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gati.hankki.member.mapper.MemberMapper;
import com.gati.hankki.member.model.LoginRequestDto;
import com.gati.hankki.member.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public Member login(LoginRequestDto dto) {
        // ID로 회원 조회
        Member dbMember = memberMapper.findByMemberId(dto.memberId());
        if (dbMember == null) return null;

        // 암호화된 비밀번호 비교
        if (!passwordEncoder.matches(dto.memberPass(), dbMember.memberPass())) {
            return null;
        }
        return dbMember;
    }

}
