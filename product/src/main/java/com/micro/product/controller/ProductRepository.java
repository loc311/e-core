package com.micro.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.product.model.Category;
import com.micro.product.model.Product;

import jakarta.validation.constraints.NotNull;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> productIds);

    Optional<Product> findByCategory(Category category);
}


