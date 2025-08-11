package com.gati.hankki.review.model;

public record ReviewRequestDto(
	    Long boardNo,
	    String reviewName,
	    String reviewContent,
	    double reviewRating
) {}
