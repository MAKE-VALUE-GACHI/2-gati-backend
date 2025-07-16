package com.gati.hankki.file.service;

import com.gati.hankki.common.exception.FileSaveFailedException;
import com.gati.hankki.file.domain.FileMeta;
import com.gati.hankki.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${upload.file.path}")
    private String uploadPath;

    private final FileMapper fileMapper;

    public List<FileMeta> storeFiles(List<MultipartFile> files, Long boardId, String createdId) {
        if (files == null || files.isEmpty()) return Collections.emptyList();

        List<FileMeta> results = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;

            try {
                String originalName = file.getOriginalFilename();
                String ext = FilenameUtils.getExtension(originalName);
                String uuid = UUID.randomUUID().toString();
                String realName = uuid + "." + ext;

                Path savePath = Paths.get(uploadPath, realName).toAbsolutePath();
                file.transferTo(savePath.toFile());

                FileMeta meta = FileMeta.builder()
                        .boardId(boardId)
                        .filePath(savePath.toString())
                        .originalFileName(originalName)
                        .realFileName(realName)
                        .fileExtension(ext)
                        .fileSize(file.getSize())
                        .createdId(createdId)
                        .createdAt(LocalDateTime.now())
                        .build();

                fileMapper.insertFile(meta);
                results.add(meta);
            } catch (IOException e) {
                log.error("파일 저장 실패", e);
                throw new FileSaveFailedException("파일 저장 실패", e);
            }
        }

        return results;
    }
}
