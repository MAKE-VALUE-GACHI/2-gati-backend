package com.gati.hankki.member.model;

public record MemberDto(
        Long id,
        String email,
        String name,
        String profileImage,
        String provider,
        String providerId
) {}