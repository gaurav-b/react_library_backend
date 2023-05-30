package com.luv2code.springbootlibrary.service.impl;


import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springbootlibrary.dao.BookRepository;
import com.luv2code.springbootlibrary.dao.CheckoutRepository;
import com.luv2code.springbootlibrary.dao.HistoryRepository;
import com.luv2code.springbootlibrary.dto.ShelfCurrentLoansDTO;
import com.luv2code.springbootlibrary.entity.Book;
import com.luv2code.springbootlibrary.entity.Checkout;
import com.luv2code.springbootlibrary.entity.History;
import com.luv2code.springbootlibrary.exception.BookAlreadyCheckedOutByUserException;
import com.luv2code.springbootlibrary.exception.BookNotFoundException;
import com.luv2code.springbootlibrary.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	private CheckoutRepository checkoutRepository;
	private HistoryRepository historyRepository;
	
	public BookServiceImpl(BookRepository bookRepository, CheckoutRepository checkoutRepository, HistoryRepository historyRepository) {
		this.bookRepository = bookRepository;
		this.checkoutRepository = checkoutRepository;
		this.historyRepository = historyRepository;
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

	@Override
	public List<ShelfCurrentLoansDTO> currentLoans(String userEmail) throws Exception {
		List<ShelfCurrentLoansDTO> shelfCurrentLoansDTOs = new ArrayList<>();
		
		List<Checkout> checkoutList = checkoutRepository.findBooksByUserEmail(userEmail);
		
		List<Long> bookIdList = new ArrayList<>();
		
		for (Checkout checkout : checkoutList) {
			bookIdList.add(checkout.getBookId());
		}
		
		// we have to do this as we don't have a mapping b/w Checkout and Book for now
		List<Book> books = bookRepository.findAllById(bookIdList);
		
		for (Book book : books) {
			Optional<Checkout> checkout = checkoutList.stream().filter(x -> x.getBookId() == book.getId()).findFirst();
			
			long diffInTime = Duration.between(LocalDate.now().atStartOfDay(), checkout.get().getReturnDate().atStartOfDay()).toMillis();
			long daysLeft = TimeUnit.MILLISECONDS.toDays(diffInTime);
			shelfCurrentLoansDTOs.add(new ShelfCurrentLoansDTO(book, (int) daysLeft));
		}
		
 		return shelfCurrentLoansDTOs;
	}

	@Override
	public void returnBook(String userEmail, Long bookId) throws Exception {
		
		Optional<Book> book = bookRepository.findById(bookId);
		
		Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
		
		if (!book.isPresent()) {
			throw new BookNotFoundException("Book doesn't exist!");
		}
		
		if (validateCheckout == null) {
			throw new BookAlreadyCheckedOutByUserException("User did not checkout this book!");
		}
		
		book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);;
		
		bookRepository.save(book.get());
		checkoutRepository.deleteById(validateCheckout.getId());
		
		History history = new History(userEmail, 
									validateCheckout.getCheckoutDate(), 
									LocalDate.now(), 
									book.get().getTitle(), 
									book.get().getAuthor(), 
									book.get().getDescription(), 
									book.get().getImg());
		
		historyRepository.save(history);
	}

	@Override
	public void renewLoan(String userEmail, Long bookId) throws Exception {
		Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);
		
		if (validateCheckout == null) {
			throw new BookAlreadyCheckedOutByUserException("User did not checkout this book!");
		}
		
		if (LocalDate.now().isBefore(validateCheckout.getReturnDate())) {
			validateCheckout.setReturnDate(LocalDate.now().plusDays(7));
			checkoutRepository.save(validateCheckout);
		}
	}

}
