package com.luv2code.springbootlibrary.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.springbootlibrary.entity.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

	Checkout findByUserEmailAndBookId(String userEmail, Long bookId);
	
	List<Checkout> findBooksByUserEmail(String userEmail);
}
