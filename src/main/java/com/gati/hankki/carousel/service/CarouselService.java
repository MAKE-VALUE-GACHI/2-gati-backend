package com.gati.hankki.carousel.service;

import com.gati.hankki.carousel.dto.CarouselResponse;
import com.gati.hankki.carousel.mapper.CarouselMapper;
import com.gati.hankki.file.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarouselService {

    private final CarouselMapper carouselMapper;
    private final FileService fileService;

    public List<CarouselResponse> findList() {
        return carouselMapper.findList().stream()
                .map(p -> new CarouselResponse(
                        p.id(),
                        p.title(),
                        p.content(),
                        p.nickname(),
                        fileService.imageUrl(p.imageUrl())
                ))
                .toList();
    }
}
