package com.luv2code.springbootlibrary.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springbootlibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Page<Book> findByTitleContaining(String title, Pageable pageable);
	Page<Book> findByCategory(String category, Pageable pageable);
	Page<Book> findAll(Pageable pageable);
}
