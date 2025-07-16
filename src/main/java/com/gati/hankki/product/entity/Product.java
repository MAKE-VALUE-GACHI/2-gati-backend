package com.gati.hankki.product.entity;

import com.gati.hankki.common.BaseEntity;
import com.gati.hankki.common.enums.BoardCategory;
import com.gati.hankki.common.enums.BoardStatus;
import com.gati.hankki.common.enums.BoardType;
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
    private String title;
    private String content;
    private Long price;
    private BoardCategory category;
    private BoardType type;
    private BoardStatus status;
    private LocalDateTime registrationDate;
}
