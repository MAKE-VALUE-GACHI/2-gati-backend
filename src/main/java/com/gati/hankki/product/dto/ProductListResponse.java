package com.gati.hankki.product.dto;

import java.time.LocalDateTime;

public record ProductListResponse(
        Long id,
        String title,
        String category,
        String nickname,
        String status,
        LocalDateTime registrationDate,
        String thumbnailUrl
) {
}

