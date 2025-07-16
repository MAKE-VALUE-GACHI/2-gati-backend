package com.gati.hankki.common.enums;

import lombok.Getter;

@Getter
public enum BoardCategory {
    KOREAN("한식"),
    WESTERN("양식"),
    SNACK("분식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    DESERT("디저트"),
    INGREDIENT("식재료"),
    ETC("기타");

    private final String label;

    BoardCategory(String label) {
        this.label = label;
    }

}
