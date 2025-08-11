package com.gati.hankki.review.service;

import java.util.List;
import java.util.Objects;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gati.hankki.review.mapper.ReviewMapper;
import com.gati.hankki.review.model.Review;
import com.gati.hankki.review.model.ReviewDetailResponseDto;
import com.gati.hankki.review.model.ReviewListResponseDto;
import com.gati.hankki.review.model.ReviewRequestDto;
import com.gati.hankki.review.model.ReviewUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

 private final ReviewMapper reviewMapper;

 @Transactional
 public void createReview(ReviewRequestDto requestDto, String userId) {
     Review review = new Review();
     review.setBoardNo(requestDto.boardNo());
     review.setReviewName(requestDto.reviewName());
     review.setReviewContent(requestDto.reviewContent());
     review.setReviewRating(requestDto.reviewRating());
     review.setCreatedId(userId);
     
     reviewMapper.insertReview(review);
 }
 
 @Transactional(readOnly = true)
 public List<ReviewListResponseDto> getReviewsByBoard(Long boardId) {
     return reviewMapper.findReviewsByBoardId(boardId);
 }

 @Transactional(readOnly = true)
 public ReviewDetailResponseDto getReviewDetail(Long reviewId) {
     Review review = reviewMapper.findReviewById(reviewId);
     if (review == null) {
         throw new IllegalArgumentException("해당 후기를 찾을 수 없습니다. id=" + reviewId);
     }
     return ReviewDetailResponseDto.from(review);
 }

 @Transactional
 public void updateReview(ReviewUpdateRequestDto requestDto, String userId) throws AccessDeniedException {
     Long reviewId = requestDto.reviewNo();
     Review review = reviewMapper.findReviewById(reviewId);

     if (review == null) {
         throw new IllegalArgumentException("해당 후기를 찾을 수 없습니다. id=" + reviewId);
     }
     
     // 작성자 본인인지 확인
     if (!Objects.equals(review.getCreatedId(), userId)) {
         throw new AccessDeniedException("후기를 수정할 권한이 없습니다.");
     }
     
     review.setReviewName(requestDto.reviewName());
     review.setReviewContent(requestDto.reviewContent());
     review.setReviewRating(requestDto.reviewRating());
     review.setUpdatedId(userId);
     
     reviewMapper.updateReview(review);
 }

 @Transactional
 public void deleteReview(Long reviewId, String userId) throws AccessDeniedException {
     Review review = reviewMapper.findReviewById(reviewId);
     if (!Objects.equals(review.getCreatedId(), userId)) {
         throw new AccessDeniedException("후기를 삭제할 권한이 없습니다.");
     }
     reviewMapper.deleteReview(reviewId);
 }

}