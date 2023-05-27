package com.luv2code.springbootlibrary.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="review")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="date")
	@CreationTimestamp
	private LocalDateTime date;
	
	@Column(name="rating")
	private double rating;
	
	@Column(name="book_id")
	private Long bookId;
	
	@Column(name="review_description")
	private String reviewDescription;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(Long id, String userEmail, LocalDateTime date, double rating, Long bookId, String reviewDescription) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.date = date;
		this.rating = rating;
		this.bookId = bookId;
		this.reviewDescription = reviewDescription;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", userEmail=" + userEmail + ", date=" + date + ", rating=" + rating + ", bookId="
				+ bookId + ", reviewDescription=" + reviewDescription + "]";
	}
	
}
