package com.luv2code.springbootlibrary.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luv2code.springbootlibrary.dao.HistoryRepository;
import com.luv2code.springbootlibrary.entity.History;
import com.luv2code.springbootlibrary.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

	private HistoryRepository historyRepository;
	
	public HistoryServiceImpl(HistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}
	
	@Override
	public Page<History> findBooksByUserEmail(String userEmail, Pageable pageable) {
		return historyRepository.findBooksByUserEmail(userEmail, pageable);
	}

}
