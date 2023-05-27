package com.luv2code.springbootlibrary.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springbootlibrary.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Page<Review> findByBookId(Long bookId, Pageable pageable);
	Review findByUserEmailAndBookId(String userEmail, Long bookId);
}
