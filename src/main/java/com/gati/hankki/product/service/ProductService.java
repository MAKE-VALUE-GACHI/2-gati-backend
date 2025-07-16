package com.gati.hankki.product.service;

import com.gati.hankki.product.dto.ProductDetail;
import com.gati.hankki.product.dto.ProductDetailResponse;
import com.gati.hankki.product.dto.ProductListResponse;
import com.gati.hankki.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;

    public List<ProductListResponse> findList(String category, String keyword, String sort) {
        log.info("ProductService findList category={}, keyword={}, sort={}", category, keyword, sort);
        return productMapper.findList(category, keyword, sort);
    }

    public ProductDetailResponse findById(Long id) {
        log.info("ProductService findById id={}", id);
        ProductDetail detail = productMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다. ID = " + id));

        List<String> images = productMapper.findImageUrlsByBoardId(id);

        return ProductDetailResponse.from(detail, images);
    }
}
