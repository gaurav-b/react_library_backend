package com.luv2code.springbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.springbootlibrary.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
