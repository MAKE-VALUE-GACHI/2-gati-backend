package com.gati.hankki.common.enums;

import lombok.Getter;

@Getter
public enum BoardType {
    SHARE("나눔"),
    TRADE("거래");

    private final String label;

    BoardType(String label) {
        this.label = label;
    }

}
