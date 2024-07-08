package com.mirco.order.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirco.order.orderline.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer>{

    List<OrderLine> findAllByOrderId(Integer orderId);

}
