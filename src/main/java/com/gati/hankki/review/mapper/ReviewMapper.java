package com.gati.hankki.review.mapper;

import com.gati.hankki.review.dto.ReviewResponse;
import com.gati.hankki.review.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    List<ReviewResponse> findReviewsByBoardId(Long boardId);

    Optional<ReviewResponse> findById(Long id);

    void insertReview(Review review);

    void updateReview(Review review);

    void deleteReview(Long id);
}
