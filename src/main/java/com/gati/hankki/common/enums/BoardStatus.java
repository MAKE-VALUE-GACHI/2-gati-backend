package com.gati.hankki.common.enums;

import lombok.Getter;

@Getter
public enum BoardStatus {
    AVAILABLE("판매중"),
    SOLD_OUT("거래완료");

    private final String label;

    BoardStatus(String label) {
        this.label = label;
    }

}
