package com.gati.hankki.product.controller;

import com.gati.hankki.product.dto.ProductDetailResponse;
import com.gati.hankki.product.dto.ProductListResponse;
import com.gati.hankki.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable Long id) {
        return ResponseEntity
                .ok(productService.findById(id));
    }

}