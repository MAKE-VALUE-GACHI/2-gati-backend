package com.gati.hankki.product.service;

import com.gati.hankki.file.service.FileService;
import com.gati.hankki.product.dto.ProductDetail;
import com.gati.hankki.product.dto.ProductDetailResponse;
import com.gati.hankki.product.dto.ProductListResponse;
import com.gati.hankki.product.dto.ProductRegisterRequest;
import com.gati.hankki.product.dto.ProductUpdateRequest;
import com.gati.hankki.product.entity.Product;
import com.gati.hankki.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;
    private final FileService fileService;

    public List<ProductListResponse> findList(String category, String keyword, String sort) {
        log.info("ProductService findList category={}, keyword={}, sort={}", category, keyword, sort);
        return productMapper.findList(category, keyword, sort).stream()
                .map(item -> new ProductListResponse(
                        item.id(),
                        item.title(),
                        item.category(),
                        item.nickname(),
                        item.status(),
                        item.registrationDate(),
                        item.imageUrl() != null ? fileService.imageUrl(item.imageUrl()) : null
                ))
                .toList();
    }

    public ProductDetailResponse findById(Long id) {
        log.info("ProductService findById id={}", id);
        ProductDetail detail = productMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다. ID = " + id));

        List<String> filePaths = productMapper.findImageUrlsByBoardId(id);

        List<String> imageUrls = filePaths.stream()
                .map(fileService::imageUrl)
                .toList();

        return ProductDetailResponse.from(detail, imageUrls);
    }

    @Transactional
    public void registerProduct(ProductRegisterRequest request, List<MultipartFile> images) {
        Long memberNo = 1L;
        String memberIdentifier = "hankki";

        Product product = Product.builder()
                .memberId(memberNo)
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .category(request.getCategory())
                .type(request.getType())
                .status(request.getStatus())
                .registrationDate(LocalDateTime.now())
                .createdId(memberIdentifier)
                .createdAt(LocalDateTime.now())
                .build();

        productMapper.insert(product);

        if (images != null && !images.isEmpty()) {
            fileService.storeFiles(images, product.getId(), memberIdentifier);
        }
    }

    @Transactional
    public void updateProduct(Long id, ProductUpdateRequest request) {
        log.info("ProductService updateProduct id={}", id);

        ProductDetail existingProduct = productMapper.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다. ID = " + id));

        if ("Y".equals(existingProduct.delYn())) {
            throw new IllegalArgumentException("삭제된 상품은 수정할 수 없습니다.");
        }

        Long memberNo = 1L;
        String memberIdentifier = "hankki";

        Product product = Product.builder()
            .id(id)
            .memberId(memberNo)
            .title(request.getTitle())
            .content(request.getContent())
            .price(request.getPrice())
            .category(request.getCategory())
            .type(request.getType())
            .status(request.getStatus())
            .registrationDate(LocalDateTime.now())
            .createdId(memberIdentifier)
            .createdAt(LocalDateTime.now())
            .build();

        productMapper.updateProduct(product);

        if (request.getImages() != null && !request.getImages().isEmpty()) {
            fileService.storeFiles(request.getImages(), product.getId(), memberIdentifier);
        }
    }

    public void deleteProduct(Long id) {
        log.info("ProductService deleteProduct id={}", id);
        ProductDetail existingProduct = productMapper.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다. ID = " + id));

        if ("Y".equals(existingProduct.delYn())) {
            throw new IllegalStateException("이미 삭제된 상품은 다시 삭제할 수 없습니다.");
        }

        productMapper.deleteById(id);
    }

}
