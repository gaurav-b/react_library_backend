package com.luv2code.springbootlibrary.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "history")
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "checkout_date")
	private LocalDate checkoutDate;
	
	@Column(name = "returned_date")
	private LocalDate returnedDate;
	
	private String title;
	private String author;
	private String description;
	private String img;
	
	public History() {
		// TODO Auto-generated constructor stub
	}

	public History(String userEamil, LocalDate checkoutDate, LocalDate returnedDate, String title,
			String author, String description, String img) {
		super();
		this.userEmail = userEamil;
		this.checkoutDate = checkoutDate;
		this.returnedDate = returnedDate;
		this.title = title;
		this.author = author;
		this.description = description;
		this.img = img;
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

	public LocalDate getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", userEmail=" + userEmail + ", checkoutDate=" + checkoutDate + ", returnedDate="
				+ returnedDate + ", title=" + title + ", author=" + author + ", description=" + description + ", img="
				+ img + "]";
	}
	
}
