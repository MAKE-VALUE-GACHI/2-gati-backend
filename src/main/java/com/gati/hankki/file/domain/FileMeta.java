package com.gati.hankki.file.domain;

import com.gati.hankki.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FileMeta extends BaseEntity {
    private Long id;
    private Long boardId;
    private String filePath;
    private String originalFileName;
    private String realFileName;
    private String fileExtension;
    private Long fileSize;
}
