package com.luv2code.springbootlibrary.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springbootlibrary.dto.ShelfCurrentLoansDTO;
import com.luv2code.springbootlibrary.entity.Book;
import com.luv2code.springbootlibrary.entity.User;
import com.luv2code.springbootlibrary.service.BookService;
import com.luv2code.springbootlibrary.service.UserService;
import com.luv2code.springbootlibrary.service.UtilityService;
import com.luv2code.springbootlibrary.util.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private BookService bookService;
	private UserService userService;
	private UtilityService utilityService;
	
	public BookController(BookService bookService, UtilityService utilityService, UserService userService) {
		this.bookService=bookService;
		this.utilityService = utilityService;
		this.userService = userService;
	}
	
	@GetMapping(path = "/{bookId}")
	public Book findBookById(@PathVariable Long bookId) {
		return bookService.findBookById(bookId);
	}
	
	@GetMapping
	public Page<Book> findAllBooks(Pageable pageable) {
		return bookService.findAllBooks(pageable);
	}
	
	@GetMapping(path = "/search/findByTitleContaining")
	public Page<Book> findAllBooksByTitleContaining(@RequestParam("title") String title, Pageable pageable) {
		return bookService.findAllBooksByTitleContaining(title, pageable);
	}
	
	@GetMapping(path = "/search/findByCategory")
	public Page<Book> findAllBooksByCategory(@RequestParam("category") String category, Pageable pageable) {
		return bookService.findAllBooksByCategory(category, pageable);
	}
	
	@PutMapping(path = "/secure/checkout")
	public Book checkoutBook(@RequestParam Long bookId) throws Exception {
		User loggedInUser = userService.fetchOne(utilityService.getLoggedInUserId()); 
		return bookService.checkoutBook(loggedInUser.getEmail(), bookId);
	}
	
	@GetMapping(path = "/secure/currentloans/count")
	public Integer chckckoutBookByUser() {
		User loggedInUser = userService.fetchOne(utilityService.getLoggedInUserId()); 
		return bookService.currentLoansCount(loggedInUser.getEmail());
	}
	
	@GetMapping(path = "/secure/ischeckedout/byuser")
	public Boolean chckckoutBookByUser(@RequestParam Long bookId) {
		User loggedInUser = userService.fetchOne(utilityService.getLoggedInUserId()); 
		return bookService.checkoutBookByUser(loggedInUser.getEmail(), bookId);
	}
	
	@GetMapping(path = "/secure/currentloans")
	public List<ShelfCurrentLoansDTO> currentLoans() throws Exception {
		User loggedInUser = userService.fetchOne(utilityService.getLoggedInUserId()); 
		return bookService.currentLoans(loggedInUser.getEmail());
	}
	
	@PutMapping(path = "/secure/return")
	public void returnBook(@RequestParam Long bookId) throws Exception {
		User loggedInUser = userService.fetchOne(utilityService.getLoggedInUserId()); 
		bookService.returnBook(loggedInUser.getEmail(), bookId);
	}
	
	@PutMapping(path = "/secure/renew/loan")
	public void renewLoan(@RequestParam Long bookId) throws Exception {
		User loggedInUser = userService.fetchOne(utilityService.getLoggedInUserId()); 
		bookService.renewLoan(loggedInUser.getEmail(), bookId);
	}
}
