package com.gati.hankki.review.controller;

import com.gati.hankki.review.dto.ReviewRegisterRequest;
import com.gati.hankki.review.dto.ReviewResponse;
import com.gati.hankki.review.dto.ReviewUpdateRequest;
import com.gati.hankki.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews", description = "Reviews API")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/board/{boardId}")
    @Operation(summary = "후기 목록 조회 API", description = "거래 후기 목록 조회")
    public ResponseEntity<List<ReviewResponse>> findReviewsByBoard(
            @Parameter(description = "거래 후기 목록 조회(상품번호)", example = "1")
            @PathVariable Long boardId
    ) {
        return ResponseEntity.ok(reviewService.findReviewsByBoard(boardId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "후기 단건 조회 API", description = "거래 후기 단건 조회")
    public ResponseEntity<ReviewResponse> findById(
            @Parameter(description = "거래 후기 단건 조회(리뷰번호)", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping
    @Operation(summary = "후기 등록 API", description = "거래 후기 등록 (별점, 내용, 이미지 파일)")
    public ResponseEntity<Void> registerReview(@RequestBody ReviewRegisterRequest request) {
        reviewService.registerReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Operation(summary = "후기 수정 API", description = "거래 후기 수정 (별점, 내용, 이미지 파일)")
    public ResponseEntity<Void> updateReview(ReviewUpdateRequest request) {
        reviewService.updateReview(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "후기 삭제 API", description = "거래 후기 삭제")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}
