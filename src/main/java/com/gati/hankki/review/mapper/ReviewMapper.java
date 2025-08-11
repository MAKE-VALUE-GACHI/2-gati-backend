package com.gati.hankki.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gati.hankki.review.model.Review;
import com.gati.hankki.review.model.ReviewListResponseDto;

@Mapper
public interface ReviewMapper {

    void insertReview(Review review);

    List<ReviewListResponseDto> findReviewsByBoardId(Long boardId);

    Review findReviewById(Long reviewId);

    void updateReview(Review review);

    void deleteReview(Long reviewId);
}
