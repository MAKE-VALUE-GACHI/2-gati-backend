package com.gati.hankki.product.mapper;

import com.gati.hankki.product.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    List<Product> findAll();

    Optional<Product> findById(Long id);
}
