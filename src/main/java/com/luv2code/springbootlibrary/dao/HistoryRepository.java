package com.luv2code.springbootlibrary.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springbootlibrary.entity.History;

public interface HistoryRepository extends JpaRepository<History, Long>{

	Page<History> findBooksByUserEmail(String userEmail, Pageable pageable);
}
