package com.gati.hankki.product.controller;

import com.gati.hankki.product.dto.ProductDetailResponse;
import com.gati.hankki.product.dto.ProductListResponse;
import com.gati.hankki.product.dto.ProductRegisterRequest;
import com.gati.hankki.product.dto.ProductUpdateRequest;
import com.gati.hankki.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Products API")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    @Operation(
            summary = "전체 상품 조회 API",
            description = "전체 상품을 내역 조회 및 카테고리, 키워드, 정렬 기준에 따라 동적 조회"
    )
    public ResponseEntity<List<ProductListResponse>> getProductList(
            @Parameter(description = "카테고리 (예: 한식, 분식 등)", example = "한식")
            @RequestParam(required = false) String category,

            @Parameter(description = "검색 키워드 (제목/내용)", example = "수제 오이피클")
            @RequestParam(required = false) String keyword,

            @Parameter(description = "정렬 방식 (recommend, latest, rating)", example = "latest")
            @RequestParam(defaultValue = "recommend") String sort
    ) {
        return ResponseEntity
                .ok(productService.findList(category, keyword, sort));
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 조회 API", description = "상품 ID(번호)로 상세 내역 조회")
    public ResponseEntity<ProductDetailResponse> getProductDetail(
            @Parameter(description = "상품 ID", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity
                .ok(productService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "상품 등록 API",
            description = "상품 등록 및 이미지 파일 업로드",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = ProductRegisterRequest.class)
                            )
                    }
            )
    )
    public ResponseEntity<Void> registerProduct(
            @RequestPart(value = "data") ProductRegisterRequest request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        productService.registerProduct(request, images);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "상품 수정 API",
        description = "상품 정보 수정 및 이미지 파일 수정"
    )
    public ResponseEntity<Void> updateProduct(
        @PathVariable Long id,
        @RequestBody ProductUpdateRequest request
    ) {
        productService.updateProduct(id, request);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "상품 삭제 API", description = "상품 삭제(논리) 처리")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

}