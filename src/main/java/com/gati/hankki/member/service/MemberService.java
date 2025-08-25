package com.gati.hankki.member.service;

import com.gati.hankki.member.model.Member;
import com.gati.hankki.member.model.MemberDto;
import com.gati.hankki.member.repository.MemberRepository;
import com.gati.hankki.oauth.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private MemberDto toDto(Member entity) {
        return new MemberDto(
                entity.getId(),
                entity.getEmail(),
                entity.getName(),
                entity.getProfileImage(),
                entity.getProvider(),
                entity.getProviderId()
        );
    }

    private Member toEntity(MemberDto dto) {
        return Member.builder()
                .id(dto.id())
                .email(dto.email())
                .name(dto.name())
                .profileImage(dto.profileImage())
                .provider(dto.provider())
                .providerId(dto.providerId())
                .build();
    }

    public MemberDto saveOrUpdate(OAuthAttributes attributes) {
        Optional<Member> existingUser = memberRepository.findByEmail(attributes.getEmail());

        Member savedEntity = existingUser
                .map(user -> { // 기존 회원이면 업데이트
                    user.updateName(attributes.getName());
                    user.updateProfileImage(attributes.getPicture());
                    return memberRepository.save(user);
                })
                .orElseGet(() -> { // 신규 회원 저장
                    Member newMember = Member.builder()
                            .email(attributes.getEmail())
                            .name(attributes.getName())
                            .profileImage(attributes.getPicture())
                            .provider(attributes.getProvider())
                            .providerId(attributes.getProviderId())
                            .build();
                    return memberRepository.save(newMember);
                });

        return toDto(savedEntity);
    }
}
