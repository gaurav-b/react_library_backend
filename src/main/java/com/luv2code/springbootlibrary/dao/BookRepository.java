package com.luv2code.springbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springbootlibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
