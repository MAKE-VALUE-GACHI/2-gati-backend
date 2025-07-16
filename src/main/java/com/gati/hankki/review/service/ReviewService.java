package com.gati.hankki.review.service;

import com.gati.hankki.review.dto.ReviewRegisterRequest;
import com.gati.hankki.review.dto.ReviewResponse;
import com.gati.hankki.review.dto.ReviewUpdateRequest;
import com.gati.hankki.review.entity.Review;
import com.gati.hankki.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public List<ReviewResponse> findReviewsByBoard(Long boardId) {
        return reviewMapper.findReviewsByBoardId(boardId);
    }

    public ReviewResponse findById(Long id) {
        return reviewMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("후기를 찾을 수 없습니다. ID = " + id));
    }


    @Transactional
    public void registerReview(ReviewRegisterRequest request) {
        Review review = Review.builder()
                .boardId(request.getBoardId())
                .name(request.getTitle())
                .content(request.getContent())
                .registrationDate(LocalDateTime.now())
                .late(request.getLate())
                .createdId("hankki")
                .createdAt(LocalDateTime.now())
                .build();
        reviewMapper.insertReview(review);
    }

    public void updateReview(ReviewUpdateRequest request) {
        Review review = Review.builder()
                .id(request.id())
                .name(request.name())
                .content(request.content())
                .late(request.late())
                .updatedId("hankki")
                .updatedAt(LocalDateTime.now())
                .build();
        reviewMapper.updateReview(review);
    }

    public void deleteReview(Long id) {
        reviewMapper.deleteReview(id);
    }

}
