package com.gati.hankki.carousel.controller;

import com.gati.hankki.carousel.dto.CarouselResponse;
import com.gati.hankki.carousel.service.CarouselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carousel")
@RequiredArgsConstructor
@Tag(name = "Carousel", description = "Carousels API")
public class CarouselController {

    private final CarouselService carouselService;

    @GetMapping()
    @Operation(summary = "캐러셀 조회 API", description = "메인 화면에 나타낼 Carousel 조회")
    public ResponseEntity<List<CarouselResponse>> getCarouselList() {
        return ResponseEntity
                .ok(carouselService.findList());
    }
    
}
