package com.gati.hankki.checklist.entity;

import com.gati.hankki.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Checklist extends BaseEntity {
    private Long checkNo;
    private Long memberNo;
    private String checklistContent;
    private String delYn;
}
