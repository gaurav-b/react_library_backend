package com.luv2code.springbootlibrary.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luv2code.springbootlibrary.dao.ReviewRepository;
import com.luv2code.springbootlibrary.dto.ReviewDTO;
import com.luv2code.springbootlibrary.entity.Review;
import com.luv2code.springbootlibrary.exception.ReviewAlreadyCreatedException;
import com.luv2code.springbootlibrary.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@Override
	public Page<Review> findReviewByBookId(Long bookId, Pageable pageable) {
		return reviewRepository.findByBookId(bookId, pageable);
	}

	@Override
	public void postReview(String userEmail, ReviewDTO reviewDTO) throws Exception {
		
		Review validatedReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewDTO.getBookId());
		
		if (validatedReview !=null) {
			throw new ReviewAlreadyCreatedException("Review already created.");
		}
		
		Review review = new Review();
		review.setBookId(reviewDTO.getBookId());
		review.setRating(reviewDTO.getRating());
		review.setUserEmail(userEmail);
		if (reviewDTO.getReviewDescription().isPresent()) {
			review.setReviewDescription(reviewDTO.getReviewDescription().map(Object::toString).orElse(null));
		}
		
		review.setDate(LocalDateTime.now());
		
		reviewRepository.save(review);
	}

	@Override
	public boolean userReviewListed(String userEmail, Long bookId) {
		Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
		
		if (validateReview!=null) {
			return true;
		}
		
		return false;
	}

	
	
}
