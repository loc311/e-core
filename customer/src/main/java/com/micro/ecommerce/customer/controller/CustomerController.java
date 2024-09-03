package com.micro.ecommerce.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.micro.ecommerce.customer.service.CustomerService;
import com.micro.ecommerce.customer.service.MessageService;
import com.micro.ecommerce.dto.ResponseGeneral;
import com.micro.ecommerce.dto.request.user.CustomerRequest;
import com.micro.ecommerce.dto.request.user.CustomerUpdateRequest;
import com.micro.ecommerce.dto.response.CustomerResponse;
import com.micro.ecommerce.model.Customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.micro.ecommerce.constant.Constant.CommonConstants.*;
import static com.micro.ecommerce.constant.Constant.MessageException.*;

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
        public ResponseGeneral<CustomerResponse> createCustomer(
                        @RequestBody @Valid CustomerRequest request,
                        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {

                log.info("(createCustomer) request: {}", request);
                return ResponseGeneral.ofCreated(
                                messageService.getMessage(CREATE_USER_SUCCESS, language),
                                customerService.createCustomer(request));
        }

        @PutMapping("/update-customer/{id}")
        public ResponseGeneral<CustomerResponse> updateCustomer(
                @PathVariable String id,
                @RequestBody @Valid CustomerUpdateRequest request,
                @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
        ){
                log.info("(updateCustomer) request: {}", request);

                
                return ResponseGeneral.ofSuccess(
                        messageService.getMessage(UPDATE_USER_SUCCESS, language),
                        customerService.updateCustomer(id,request)
                        );
        }

       @GetMapping
       public ResponseGeneral<List<CustomerResponse>> getAllCustomers( 
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
       ) {
        log.info("(getAllCustomers)");
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(FIND_ALL_CUSTOMERS_SUCCESS, language),
                customerService.getAllCustomers()
        );
       }

       @GetMapping("/{id}")
       public ResponseGeneral<Customer> findByEmail(
                        @PathVariable String email,
                        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
                log.info("(findById) email: {}", email);
                return ResponseGeneral.ofSuccess(
                                messageService.getMessage("find.customer.success", language),
                                customerService.getCustomerByEmail(email));
        }

        // @GetMapping("/exists/{customer-id}")
        // public ResponseGeneral<Boolean> existsById(
        //                 @PathVariable("customer-id") String customerId,
        //                 @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        //         log.info("(existsById) customerId: {}", customerId);
        //         return ResponseGeneral.ofSuccess(
        //                         messageService.getMessage("check.customer.exists.success", language),
        //                         customerService.existsById(customerId));
        // }

        // @GetMapping("/{customer-id}")
        // public ResponseGeneral<CustomerResponse> findById(
        //                 @PathVariable("customer-id") String customerId,
        //                 @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        //         log.info("(findById) customerId: {}", customerId);
        //         return ResponseGeneral.ofSuccess(
        //                         messageService.getMessage("find.customer.success", language),
        //                         customerService.findById(customerId));
        // }

        // @DeleteMapping("/{customer-id}")
        // public ResponseGeneral<Void> deleteById(
        //                 @PathVariable("customer-id") String customerId,
        //                 @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        //         log.info("(deleteById) customerId: {}", customerId);
        //         customerService.deleteById(customerId);
        //         return ResponseGeneral.ofSuccess(
        //                         messageService.getMessage("delete.customer.success", language));
        // }
}
