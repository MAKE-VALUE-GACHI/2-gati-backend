package com.gati.hankki.product.controller;

import com.gati.hankki.product.model.Product;
import com.gati.hankki.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Products API")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    @Operation(summary = "전체 상품 조회 API", description = "전체 상품을 내역 조회")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> findAll = productService.findAll();
        return ResponseEntity
                .ok(findAll);
    }

    @GetMapping("/{id}")
    @Operation(summary = "상품 상세 조회 API", description = "상품 ID(번호)로 상세 내역 조회")
    public ResponseEntity<Product> getProductDetail(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity
                .ok(product);
    }
}