package com.mirco.order.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.mirco.order.dto.CustomerResponse;
import com.mirco.order.model.PaymentMethod;
import com.mirco.order.product.PurchaseResponse;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products) {

}
