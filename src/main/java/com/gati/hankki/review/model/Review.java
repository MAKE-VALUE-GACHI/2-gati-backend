package com.gati.hankki.review.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Review {
	
	private Long reviewNo;
    private Long boardNo;
    private String reviewName;
    private String reviewContent;
    private LocalDateTime reviewTime;
    private double reviewRating;
    private String createdId;
    private LocalDateTime createdAt;
    private String updatedId;
    private LocalDateTime updatedAt;
    public String getCreatedId() {
        return this.createdId;
    }


}
