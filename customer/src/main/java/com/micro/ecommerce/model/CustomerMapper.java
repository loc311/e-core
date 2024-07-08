package com.micro.ecommerce.model;

import org.springframework.stereotype.Service;

import com.micro.ecommerce.request.CustomerRequest;
import com.micro.ecommerce.response.CustomerReponse;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if(request == null) {
            return null;
        }
        return Customer.builder()
            .id(request.id())
            .firstname(request.firstname())
            .lastname(request.lastname())
            .email(request.email())
            .address(request.address())
            .build();
    }    

    public CustomerReponse fromCustomer(Customer customer){
            return new CustomerReponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
            );
    }
}
