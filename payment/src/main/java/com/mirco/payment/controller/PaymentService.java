package com.mirco.payment.controller;

import org.springframework.stereotype.Service;

import com.mirco.payment.dto.PaymentMapper;
import com.mirco.payment.dto.PaymentRequest;
import com.mirco.payment.model.PaymentRepository;
import com.mirco.payment.notification.NotificationProducer;
import com.mirco.payment.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository repository;
  private final PaymentMapper mapper;
  private final NotificationProducer notificationProducer;

  public Integer createPayment(PaymentRequest request) {
    var payment = this.repository.save(this.mapper.toPayment(request));

    this.notificationProducer.sendNotification(
            new PaymentNotificationRequest(
                    request.orderReference(),
                    request.amount(),
                    request.paymentMethod(),
                    request.customer().firstname(),
                    request.customer().lastname(),
                    request.customer().email()
            )
    );
    return payment.getId();
  }
}
