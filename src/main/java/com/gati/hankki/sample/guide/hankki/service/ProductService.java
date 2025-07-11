package com.gati.hankki.sample.guide.hankki.service;

import com.gati.hankki.sample.guide.hankki.mapper.ProductMapper;
import com.gati.hankki.sample.guide.hankki.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;

    public List<Product> findAll() {
        log.info("ProductService findAll");
        return productMapper.findAll();
    }

    public Product findById(Long id) {
        log.info("ProductService findById id={}", id);
        return productMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다. ID=" + id));
    }

}
