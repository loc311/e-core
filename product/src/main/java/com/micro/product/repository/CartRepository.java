package com.micro.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.product.model.Cart;

//Integer là kiểu dữ liệu của primary key trong cart
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}
