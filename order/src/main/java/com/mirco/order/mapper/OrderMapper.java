package com.mirco.order.mapper;

import org.springframework.stereotype.Service;

import com.mirco.order.dto.OrderRequest;
import com.mirco.order.dto.OrderResponse;
import com.mirco.order.model.Order;

@Service
public class OrderMapper {

    public Order toOrder (OrderRequest request) {
        if(request == null) {
            return null;
        }
        return Order.builder()
            .id(request.id())
            .reference(request.reference())
            .paymentMethod(request.paymentMethod())
            .customerId(request.customerId())
            .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getReference(),
            order.getTotalAmount(),
            order.getPaymentMethod(),
            order.getCustomerId()
        );
    }
}
