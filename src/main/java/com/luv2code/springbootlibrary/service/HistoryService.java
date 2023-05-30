package com.luv2code.springbootlibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luv2code.springbootlibrary.entity.History;

public interface HistoryService {

	Page<History> findBooksByUserEmail(String userEmail, Pageable pageable);
}
