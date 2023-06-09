package com.luv2code.springbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.springbootlibrary.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
