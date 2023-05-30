package com.luv2code.springbootlibrary.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luv2code.springbootlibrary.dto.ShelfCurrentLoansDTO;
import com.luv2code.springbootlibrary.entity.Book;

public interface BookService {

	public Book findBookById(Long bookId);
	public Page<Book> findAllBooks(Pageable pageable);
	public Page<Book> findAllBooksByTitleContaining(String title, Pageable pageable);
	public Page<Book> findAllBooksByCategory(String category, Pageable pageable);
	
	public Book checkoutBook(String userEmail, Long bookId) throws Exception;
	public Boolean checkoutBookByUser(String userEmail, Long bookId);
	
	public Integer currentLoansCount(String userEmail);
	
	public List<ShelfCurrentLoansDTO> currentLoans(String userEmail) throws Exception;
	
	public void returnBook (String userEmail, Long bookId) throws Exception;
	
	public void renewLoan (String userEmail, Long bookId) throws Exception;
}
