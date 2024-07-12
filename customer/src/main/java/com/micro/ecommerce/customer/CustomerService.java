package com.micro.ecommerce.customer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.micro.ecommerce.exception.CustomerNotFoundException;
import com.micro.ecommerce.model.Customer;
import com.micro.ecommerce.model.CustomerMapper;
import com.micro.ecommerce.model.CustomerRepository;
import com.micro.ecommerce.request.CustomerRequest;
import com.micro.ecommerce.response.CustomerReponse;
import org.apache.commons.lang.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        if (repository.existsByEmail(request.email())) {
            return "Email already exists!";
        } else {
        var customer = repository.save(customerMapper.toCustomer(request));
        return customer.getId(); 
    }
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with id: %s", request.id())));
        mergerCustomer(customer, request);
        repository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        };

        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        };
    
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        };
        if (Objects.nonNull(request.address())) {
            customer.setAddress(request.address());
        }
        
      }

    public List<CustomerReponse> findAllCustomer() {
        return repository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerReponse findById(String customerId) {
        return repository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with id: %s", customerId)));
    }

    public void deleteById(String customerId) {
        repository.deleteById(customerId);
    }
}
