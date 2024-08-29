package com.micro.ecommerce.customer.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.micro.ecommerce.dto.request.user.CustomerRequest;
import com.micro.ecommerce.dto.response.CustomerReponse;
import com.micro.ecommerce.exception.CustomerNotFoundException;
import com.micro.ecommerce.model.Customer;
import com.micro.ecommerce.ultils.MapperUtils;
import com.micro.ecommerce.model.CustomerRepository;

import org.apache.commons.lang.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public String createCustomer(CustomerRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            return "Email already exists!";
        } else {
            var customer = repository.save(MapperUtils.toEntity(request, Customer.class));
            return customer.getId(); 
        }
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(String.valueOf(request.getUser_id()))
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with id: %s", request.getUser_id())));
        mergerCustomer(customer, request);
        repository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.getFirstname())) {
            customer.setFirstname(request.getFirstname());
        };

        if (StringUtils.isNotBlank(request.getLastname())) {
            customer.setLastname(request.getLastname());
        };
    
        if (StringUtils.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
        
      }

    public List<CustomerReponse> findAllCustomer() {
        return MapperUtils.toDTOs(repository.findAll(), CustomerReponse.class);
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerReponse findById(String customerId) {
        return repository.findById(customerId)
                .map(customer -> MapperUtils.toDTO(customer, CustomerReponse.class))
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with id: %s", customerId)));
    }

    public void deleteById(String customerId) {
        repository.deleteById(customerId);
    }
}
