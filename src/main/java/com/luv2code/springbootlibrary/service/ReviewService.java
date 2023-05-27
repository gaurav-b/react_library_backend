package com.luv2code.springbootlibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luv2code.springbootlibrary.dto.ReviewDTO;
import com.luv2code.springbootlibrary.entity.Review;

public interface ReviewService {

	public Page<Review> findReviewByBookId(Long bookId, Pageable pageable);
	
	public void postReview(String userEmail, ReviewDTO reviewDTO) throws Exception;
	
	public boolean userReviewListed(String userEmail, Long bookId);
}
