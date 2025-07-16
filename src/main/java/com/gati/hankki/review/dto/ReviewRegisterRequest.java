package com.gati.hankki.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "리뷰 등록 요청 DTO")
public class ReviewRegisterRequest {
    @Schema(description = "상품번호", example = "1")
    private Long boardId;

    @Schema(description = "후기 제목", example = "나눔 감사해요.")
    private String title;

    @Schema(description = "후기 내용", example = "정말 맛있어요.")
    private String content;

    @Schema(description = "후기 평점", example = "4.5")
    private Double late;
}
