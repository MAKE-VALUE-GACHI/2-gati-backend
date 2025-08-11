package com.gati.hankki.member.model;

import java.time.LocalDateTime;

public record Member(
		Long memberNo,
		String memberId,
		String memberPass,
		String memberName,
		String memberAddress,
		Long memberAge,
		String outYn,
		String delYn,
		Long authCode,
		String authYn,
		String createdId,
		LocalDateTime createdAt,
		String updatedId,
		LocalDateTime updatedAt
) {
}

