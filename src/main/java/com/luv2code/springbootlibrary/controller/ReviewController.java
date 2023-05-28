package com.luv2code.springbootlibrary.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springbootlibrary.dto.ReviewDTO;
import com.luv2code.springbootlibrary.entity.Review;
import com.luv2code.springbootlibrary.service.ReviewService;
import com.luv2code.springbootlibrary.service.UserService;
import com.luv2code.springbootlibrary.service.UtilityService;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	private ReviewService reviewService;
	private UserService userService;
	private UtilityService utilityService;
	
	public ReviewController(ReviewService reviewService, UserService userService, UtilityService utilityService) {
		this.reviewService = reviewService;
		this.userService = userService;
		this.utilityService = utilityService;
	}
	
	@GetMapping("/search/findByBookId")
	public Page<Review> findAllReviewsByBookId(@RequestParam("bookId") Long bookId, Pageable pageable) {
		return reviewService.findReviewByBookId(bookId, pageable);
	}
	
	@PostMapping("/secure")
	public void postReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
		reviewService.postReview(userService.fetchOne(utilityService.getLoggedInUserId()).getEmail(), reviewDTO);
	}

	@GetMapping("/secure/user/book")
	public Boolean reviewBookByUser(@RequestParam("bookId") Long bookId) throws Exception {
		return reviewService.userReviewListed(userService.fetchOne(utilityService.getLoggedInUserId()).getEmail(), bookId);
	}
}
