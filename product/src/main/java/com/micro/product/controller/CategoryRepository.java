package com.micro.product.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.product.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    
}
