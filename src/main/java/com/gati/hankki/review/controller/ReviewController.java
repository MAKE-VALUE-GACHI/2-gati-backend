package com.gati.hankki.review.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gati.hankki.review.model.ReviewUpdateRequestDto; // 수정용 DTO 임포트
import com.gati.hankki.review.model.ReviewDetailResponseDto;
import com.gati.hankki.review.model.ReviewListResponseDto;
import com.gati.hankki.review.model.ReviewRequestDto;
import com.gati.hankki.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews") // 공통 URL 매핑
public class ReviewController {

    private final ReviewService reviewService;

    private String getAuthenticatedUserId() {
        return "testUser";
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewRequestDto requestDto) {
        String userId = getAuthenticatedUserId();
        reviewService.createReview(requestDto, userId);
        return ResponseEntity.ok("후기가 성공적으로 등록되었습니다.");
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<ReviewListResponseDto>> getReviewsByBoard(@PathVariable Long boardId) {
        List<ReviewListResponseDto> reviews = reviewService.getReviewsByBoard(boardId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDetailResponseDto> getReviewDetail(@PathVariable("id") Long reviewId) {
        ReviewDetailResponseDto review = reviewService.getReviewDetail(reviewId);
        return ResponseEntity.ok(review);
    }

    @PutMapping
    public ResponseEntity<String> updateReview(@RequestBody ReviewUpdateRequestDto requestDto) throws AccessDeniedException {
        String userId = getAuthenticatedUserId();
        reviewService.updateReview(requestDto, userId);
        return ResponseEntity.ok("후기가 성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long reviewId) throws AccessDeniedException {
        String userId = getAuthenticatedUserId();
        reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.ok("후기가 성공적으로 삭제되었습니다.");
    }
}