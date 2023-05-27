package com.luv2code.springbootlibrary.dto;

import java.util.Optional;

public class ReviewDTO {
	
	private double rating;
	private Long bookId;
	private Optional<String> reviewDescription;
	
	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(double rating, Long bookId, Optional<String> reviewDescription) {
		super();
		this.rating = rating;
		this.bookId = bookId;
		this.reviewDescription = reviewDescription;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Optional<String> getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(Optional<String> reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	@Override
	public String toString() {
		return "ReviewDTO [rating=" + rating + ", bookId=" + bookId + ", reviewDescription=" + reviewDescription + "]";
	}
	
}
