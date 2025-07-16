package com.gati.hankki.file.mapper;

import com.gati.hankki.file.domain.FileMeta;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    void insertFile(FileMeta fileMeta);
}
