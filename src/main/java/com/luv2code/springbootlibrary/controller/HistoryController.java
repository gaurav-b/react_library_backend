package com.luv2code.springbootlibrary.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springbootlibrary.entity.History;
import com.luv2code.springbootlibrary.service.HistoryService;

@RestController
@RequestMapping("/api/histories")
public class HistoryController {

	private HistoryService historyService;
	
	public HistoryController(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	@GetMapping(path = "/secure/search/books/usereamail")
	public Page<History> chckckoutBookByUser(@RequestParam String userEmail, Pageable pageable) {
		return historyService.findBooksByUserEmail(userEmail, pageable);
	}
}
