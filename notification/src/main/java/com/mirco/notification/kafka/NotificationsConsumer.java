package com.mirco.notification.kafka;

import static com.mirco.notification.notification.NotificationType.ORDER_CONFIRMATION;
import static com.mirco.notification.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mirco.notification.email.EmailService;
import com.mirco.notification.kafka.order.OrderConfirmation;
import com.mirco.notification.kafka.payment.PaymentConfirmation;
import com.mirco.notification.notification.Notification;
import com.mirco.notification.notification.NotificationRepository;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {

    private final NotificationRepository repo;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {

        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repo.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build());
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repo.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build());
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products());
    }
}
