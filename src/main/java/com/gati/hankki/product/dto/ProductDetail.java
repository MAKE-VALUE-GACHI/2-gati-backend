package com.gati.hankki.product.dto;

import java.time.LocalDateTime;

public record ProductDetail(
        Long id,
        String title,
        String content,
        String category,
        String status,
        String nickname,
        LocalDateTime registrationDate
) {
}
