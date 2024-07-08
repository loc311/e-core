package com.mirco.payment.dto;

import java.math.BigDecimal;

import com.mirco.payment.model.Customer;
import com.mirco.payment.model.PaymentMethod;

public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    Customer customer
) {
}
