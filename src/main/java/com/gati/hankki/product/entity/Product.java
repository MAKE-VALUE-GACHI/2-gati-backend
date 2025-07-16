package com.gati.hankki.product.entity;

import com.gati.hankki.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    private Long id;
    private Long memberId;
    private String name;
    private String content;
    private LocalDateTime registrationDate;
    private String category;
    private String status;
}
