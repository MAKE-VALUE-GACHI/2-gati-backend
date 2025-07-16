package com.gati.hankki.product.mapper;

import com.gati.hankki.product.dto.ProductDetail;
import com.gati.hankki.product.dto.ProductListResponse;
import com.gati.hankki.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    List<ProductListResponse> findList(String category, String keyword, String sort);

    Optional<ProductDetail> findById(Long id);

    List<String> findImageUrlsByBoardId(Long boardId);

    void insert(Product product);

}
