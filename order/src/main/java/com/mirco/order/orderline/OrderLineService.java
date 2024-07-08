package com.mirco.order.orderline;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mirco.order.controller.OrderLineResponse;
import com.mirco.order.dto.OrderLineRequest;
import com.mirco.order.mapper.OrderLineMapper;
import com.mirco.order.model.repo.OrderLineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {

        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }

    public List<OrderLineResponse> finAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId)
                            .stream()
                            .map(mapper::toOrderLineResponse)
                            .collect(Collectors.toList());   
}
}
