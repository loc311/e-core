package com.mirco.order.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirco.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
