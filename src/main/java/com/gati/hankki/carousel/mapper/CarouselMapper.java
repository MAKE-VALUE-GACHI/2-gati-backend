package com.gati.hankki.carousel.mapper;

import com.gati.hankki.carousel.dto.CarouselResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarouselMapper {
    List<CarouselResponse> findList();
}
