package com.mirco.order.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mirco.order.dto.PaymentRequest;

@FeignClient(
    name = "product-service",
    url = "${application.config.payment-url}"
)
public interface PaymentClient {

    @PostMapping
    Integer requestOrderPayment(@RequestBody PaymentRequest request);
}
