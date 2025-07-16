package com.gati.hankki.review.dto;

public record ReviewUpdateRequest(
        Long id,
        String name,
        String content,
        Double late
) {
}
