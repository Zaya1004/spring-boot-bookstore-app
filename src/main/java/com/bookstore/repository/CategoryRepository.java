package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<com.bookstore.entity.Category, Long>{
	
}
