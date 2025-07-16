package com.gati.hankki.file.service;

import com.gati.hankki.common.exception.FileSaveFailedException;
import com.gati.hankki.file.domain.FileMeta;
import com.gati.hankki.file.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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

    @Value("${static.file.root.path}")
    private String rootPath;

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

    public Resource loadFile(String relativePath) {
        try {
            Path filePath = Paths.get(rootPath).resolve(relativePath).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new FileNotFoundException("파일을 찾을 수 없습니다: " + relativePath);
            }

            return resource;
        } catch (MalformedURLException | FileNotFoundException e) {
            log.error("파일 로드 실패: {}", relativePath, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "파일을 찾을 수 없습니다.");
        }
    }

    public String getFileUrl(String fullPath) {
        if (fullPath == null) return null;

        String relativePath = fullPath.replace(rootPath, "");
        if (relativePath.startsWith("/")) {
            relativePath = relativePath.substring(1);
        }

        return "/api/v1/files/view/" + relativePath;
    }
}
