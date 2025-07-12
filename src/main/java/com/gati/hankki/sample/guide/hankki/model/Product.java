package com.gati.hankki.sample.guide.hankki.model;

import java.time.LocalDateTime;

public record Product(
        Long id,
        Long memberId,
        String name,
        String content,
        LocalDateTime registrationDate,
        String category,
        String status,
        String createdBy,
        LocalDateTime createdAt,
        String updatedBy,
        LocalDateTime updatedAt
) {
}

