package com.mirco.order.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mirco.order.customer.CustomerClient;
import com.mirco.order.dto.OrderLineRequest;
import com.mirco.order.dto.OrderRequest;
import com.mirco.order.dto.OrderResponse;
import com.mirco.order.dto.PaymentRequest;
import com.mirco.order.exception.BusinessException;
import com.mirco.order.kafka.OrderConfirmation;
import com.mirco.order.kafka.OrderProducer;
import com.mirco.order.mapper.OrderMapper;
import com.mirco.order.model.Order;
import com.mirco.order.model.repo.OrderRepository;
import com.mirco.order.orderline.OrderLineService;
import com.mirco.order.payment.PaymentClient;
import com.mirco.order.product.ProductClient;
import com.mirco.order.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest request) {

        // check the customer-> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Can not create order:: No customer exists"));
        // purchase the products -> product-ms (rest-template)
        var purchasedProducts = productClient.purchaseProducts(request.products());
        // persist order
        var order = this.repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()));
        }
        // start payment
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer);
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation -> kafka
        orderProducer.sendOrderConfirmation(
            new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
            )
        );

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.repository.findById(id)
                            .map(this.mapper::fromOrder)
                            .orElseThrow(() -> new EntityNotFoundException(String.format("No Order found with the provided ID: %d", id)));
    }
}
