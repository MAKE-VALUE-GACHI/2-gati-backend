package com.gati.hankki.product.dto;

import com.gati.hankki.common.enums.BoardCategory;
import com.gati.hankki.common.enums.BoardStatus;
import com.gati.hankki.common.enums.BoardType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "상품 등록 요청 DTO")
public class ProductRegisterRequest {
    @Schema(description = "상품명", example = "수제 오이피클")
    private String title;

    @Schema(description = "상품 설명", example = "국내산 오이로 만든 수제 피클입니다.")
    private String content;

    @Schema(description = "카테고리", example = "KOREAN")
    private BoardCategory category;

    @Schema(description = "가격", example = "3500")
    private Long price;

    @Schema(description = "거래 방식", example = "TRADE")
    private BoardType type;

    @Schema(description = "상품 상태", example = "AVAILABLE")
    private BoardStatus status;

    @Schema(description = "이미지 파일 리스트")
    private List<MultipartFile> images;
}
