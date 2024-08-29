package com.micro.ecommerce.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.micro.ecommerce.customer.service.CustomerService;
import com.micro.ecommerce.customer.service.MessageService;
import com.micro.ecommerce.dto.ResponseGeneral;
import com.micro.ecommerce.dto.request.user.CustomerRequest;
import com.micro.ecommerce.dto.response.CustomerReponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private static final String LANGUAGE = "Accept-Language";
    private static final String DEFAULT_LANGUAGE = "en";

    private final CustomerService customerService;
    private final MessageService messageService;

    @PostMapping
    public ResponseGeneral<String> createCustomer(
            @RequestBody @Valid CustomerRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(createCustomer) request: {}", request);
        return ResponseGeneral.ofCreated(
                messageService.getMessage("create.customer.success", language),
                customerService.createCustomer(request)
        );
    }

    @PutMapping
    public ResponseGeneral<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(updateCustomer) request: {}", request);
        customerService.updateCustomer(request);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage("update.customer.success", language)
        );
    }

    @GetMapping
    public ResponseGeneral<List<CustomerReponse>> findAll(
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(findAll)");
        return ResponseGeneral.ofSuccess(
                messageService.getMessage("find.all.customers.success", language),
                customerService.findAllCustomer()
        );
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseGeneral<Boolean> existsById(
            @PathVariable("customer-id") String customerId,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(existsById) customerId: {}", customerId);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage("check.customer.exists.success", language),
                customerService.existsById(customerId)
        );
    }

    @GetMapping("/{customer-id}")
    public ResponseGeneral<CustomerReponse> findById(
            @PathVariable("customer-id") String customerId,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(findById) customerId: {}", customerId);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage("find.customer.success", language),
                customerService.findById(customerId)
        );
    }

    @DeleteMapping("/{customer-id}")
    public ResponseGeneral<Void> deleteById(
            @PathVariable("customer-id") String customerId,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(deleteById) customerId: {}", customerId);
        customerService.deleteById(customerId);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage("delete.customer.success", language)
        );
    }
}
