package com.luv2code.springbootlibrary.dto;

import com.luv2code.springbootlibrary.entity.Book;

public class ShelfCurrentLoansDTO {

	private Book book;
	private int daysLeft;
	
	private void ShelfCurrentLoansDTo() {
		// TODO Auto-generated method stub

	}
	
	public ShelfCurrentLoansDTO(Book book, int daysLeft) {
		this.book = book;
		this.daysLeft = daysLeft;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}

	@Override
	public String toString() {
		return "ShelfCurrentLoansDTO [book=" + book + ", daysLeft=" + daysLeft + ", getBook()=" + getBook()
				+ ", getDaysLeft()=" + getDaysLeft() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
