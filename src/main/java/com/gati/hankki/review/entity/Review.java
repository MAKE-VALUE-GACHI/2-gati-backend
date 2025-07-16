package com.gati.hankki.review.entity;

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
public class Review extends BaseEntity {
    private Long id;
    private Long boardId;
    private String name;
    private String content;
    private LocalDateTime registrationDate;
    private double late;
}
