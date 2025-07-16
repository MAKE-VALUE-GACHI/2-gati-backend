package com.gati.hankki.file.controller;

import com.gati.hankki.file.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    public static final String PREFIX = "/api/v1/files/view/";
    private final FileService fileService;

    @GetMapping("/view/**")
    public ResponseEntity<Resource> serveFile(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String relativePath = uri.substring(uri.indexOf(PREFIX) + PREFIX.length());

        Resource file = fileService.loadFile(relativePath);

        return ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType(file).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(file);
    }
}
