package com.mirco.order.dto;

import java.math.BigDecimal;

import com.mirco.order.model.PaymentMethod;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {

}
