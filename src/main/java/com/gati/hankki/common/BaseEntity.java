package com.gati.hankki.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    private String createdId;
    private LocalDateTime createdAt;
    private String updatedId;
    private LocalDateTime updatedAt;
}
