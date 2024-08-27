package com.micro.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.product.model.Category;
import com.micro.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findAllByIdInOrderById(List<Integer> productIds);

    Optional<Product> findByCategory(Category category);
}


