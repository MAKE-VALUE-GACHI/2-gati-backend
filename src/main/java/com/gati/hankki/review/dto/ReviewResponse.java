package com.gati.hankki.review.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String title,
        String content,
        LocalDateTime registrationDate,
        Double late
) {
}
