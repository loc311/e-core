package com.mirco.order.dto;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        Integer productId,
        double quantity) {

}
