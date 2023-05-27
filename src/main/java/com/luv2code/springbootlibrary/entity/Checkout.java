package com.luv2code.springbootlibrary.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "checkout")
public class Checkout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "checkout_date")
	private LocalDate checkoutDate;
	
	@Column(name = "return_date")
	private LocalDate returnDate;
	
	@Column(name = "book_id")
	private Long bookId;
	
	public Checkout() {
		
	}
	
	public Checkout(String userEmail, LocalDate checkoutDate, LocalDate returnDate, Long bookId) {
		this.userEmail=userEmail;
		this.checkoutDate=checkoutDate;
		this.returnDate=returnDate;
		this.bookId=bookId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "Checkout [id=" + id + ", userEmail=" + userEmail + ", checkoutDate=" + checkoutDate + ", returnDate="
				+ returnDate + ", bookId=" + bookId + "]";
	}
	
}
