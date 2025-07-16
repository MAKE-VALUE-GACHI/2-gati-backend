package com.gati.hankki.product.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDetailResponse(
        Long id,
        String title,
        String content,
        String category,
        String status,
        String nickname,
        LocalDateTime registrationDate,
        List<String> imageUrls
) {
    public static ProductDetailResponse from(ProductDetail core, List<String> imageUrls) {
        return new ProductDetailResponse(
                core.id(),
                core.title(),
                core.content(),
                core.category(),
                core.status(),
                core.nickname(),
                core.registrationDate(),
                imageUrls
        );
    }
}
