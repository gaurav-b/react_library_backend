package com.luv2code.springbootlibrary.service.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springbootlibrary.dao.BookRepository;
import com.luv2code.springbootlibrary.dao.CheckoutRepository;
import com.luv2code.springbootlibrary.entity.Book;
import com.luv2code.springbootlibrary.entity.Checkout;
import com.luv2code.springbootlibrary.exception.BookAlreadyCheckedOutByUserException;
import com.luv2code.springbootlibrary.exception.BookNotFoundException;
import com.luv2code.springbootlibrary.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	private CheckoutRepository checkoutRepository;
	
	public BookServiceImpl(BookRepository bookRepository, CheckoutRepository checkoutRepository) {
		this.bookRepository = bookRepository;
		this.checkoutRepository = checkoutRepository;
	}
	
	@Override
	public Book findBookById(Long bookId) {
		return bookRepository.findById(bookId).get();
	}

	@Override
	public Page<Book> findAllBooks(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@Override
	public Page<Book> findAllBooksByTitleContaining(String title, Pageable pageable) {
		return bookRepository.findByTitleContaining(title, pageable);
	}

	@Override
	public Page<Book> findAllBooksByCategory(String category, Pageable pageable) {
		return bookRepository.findByCategory(category, pageable);
	}

	@Override
	@Transactional
	public Book checkoutBook(String userEmail, Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);
		Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
		
		if (!book.isPresent() || book.get().getCopiesAvailable() <=0) {
			throw new BookNotFoundException("Book doesn't exist or not available for now!");
		}
		
		if (validateCheckout != null) {
			throw new BookAlreadyCheckedOutByUserException("Book already has been checked out by user");
		}
		
		book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
		bookRepository.save(book.get());
		
		Checkout checkout = new Checkout(userEmail, LocalDate.now(), LocalDate.now().plusDays(7), book.get().getId());
		checkoutRepository.save(checkout);
		
		return book.get();
	}

	@Override
	public Boolean checkoutBookByUser(String userEmail, Long bookId) {
		Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
		if (validateCheckout!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Integer currentLoansCount(String userEmail) {
		return checkoutRepository.findBooksByUserEmail(userEmail).size();
	}

}
