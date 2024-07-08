package com.mirco.order.mapper;

import org.springframework.stereotype.Service;

import com.mirco.order.controller.OrderLineResponse;
import com.mirco.order.dto.OrderLineRequest;
import com.mirco.order.model.Order;
import com.mirco.order.orderline.OrderLine;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {

        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(), 
                orderLine.getQuantity() );
    }

}
