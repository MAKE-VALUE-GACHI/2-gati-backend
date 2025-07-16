package com.gati.hankki.carousel.dto;

public record CarouselResponse(
        Long id,
        String title,
        String content,
        String nickname,
        String imageUrl
) {
}
