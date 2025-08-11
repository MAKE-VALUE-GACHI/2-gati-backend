package com.gati.hankki.review.model;

import java.time.LocalDateTime;

public record ReviewDetailResponseDto(
    Long reviewNo,
    Long boardNo,
    String reviewName,
    String reviewContent,
    double reviewRating,
    String createdId,
    LocalDateTime createdAt
) {
    public static ReviewDetailResponseDto from(Review review) {
        return new ReviewDetailResponseDto(
            review.getReviewNo(),
            review.getBoardNo(),
            review.getReviewName(),
            review.getReviewContent(),
            review.getReviewRating(),
            review.getCreatedId(),
            review.getCreatedAt()
        );
    }
}
