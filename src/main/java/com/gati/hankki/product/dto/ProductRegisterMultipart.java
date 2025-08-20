package com.gati.hankki.product.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Schema(name = "ProductRegisterMultipart", description = "상품 등록 멀티파트 요청 body")
public record ProductRegisterMultipart(
    @Schema(description = "상품 등록 데이터(JSON)", implementation = ProductRegisterRequest.class)
    ProductRegisterRequest data,

    @ArraySchema(
        schema = @Schema(type = "string", format = "binary"),
        arraySchema = @Schema(description = "업로드 이미지 파일 리스트")
    )
    List<MultipartFile> images
) {

}