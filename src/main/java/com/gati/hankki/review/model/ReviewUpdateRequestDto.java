package com.gati.hankki.review.model;

public record ReviewUpdateRequestDto(
	    Long reviewNo, 
	    String reviewName,
	    String reviewContent,
	    double reviewRating
) {}
