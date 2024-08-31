package com.micro.ecommerce.customer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.micro.ecommerce.customer.service.CustomerService;
import com.micro.ecommerce.dto.request.user.ChangePasswordRequest;
import com.micro.ecommerce.dto.request.user.CustomerRequest;
import com.micro.ecommerce.dto.response.CustomerResponse;
import com.micro.ecommerce.exception.user.EmailAlreadyExistException;
import com.micro.ecommerce.exception.user.PasswordIncorrectException;
import com.micro.ecommerce.exception.user.UserNotFoundException;
import com.micro.ecommerce.model.Customer;
import com.micro.ecommerce.model.CustomerRepository;
import com.micro.ecommerce.ultils.MapperUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.micro.ecommerce.ultils.BCryptUtils.getPasswordEncoder;
import static com.micro.ecommerce.ultils.MapperUtils.MODEL_MAPPER;

@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        log.info("Create customer: {}", customerRequest);

        checkExistedEmail(customerRequest.getEmail());

        Customer customer = MapperUtils.toEntity(customerRequest, Customer.class);
        customer.setPassword(getPasswordEncoder().encode(customerRequest.getPassword()));

        return MapperUtils.toDTO(customerRepository.save(customer), CustomerResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerByEmail(String email) {
        log.info("(getByEmail) email: {}", email);

        return customerRepository.getByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> MapperUtils.toDTO(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(String id, CustomerRequest customerRequest) {
        log.info("Update customer with id: {}", id);

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(customerRequest.getPassword(), existingCustomer.getPassword())) {
            throw new PasswordIncorrectException();
        }

        existingCustomer.setFirstname(customerRequest.getFirstname());
        existingCustomer.setLastname(customerRequest.getLastname());
        existingCustomer.setEmail(customerRequest.getEmail());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return MapperUtils.toDTO(updatedCustomer, CustomerResponse.class);
    }

    @Override
    @Transactional
    public void deleteCustomer(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }

   
    @Override
    public CustomerResponse detailCustomer(String id) {
        log.info("Detail customer with id: {}", id);

        Customer customer = find(id);

        return MapperUtils.toDTO(customer, CustomerResponse.class);
    }

    @Override
    @Transactional
    public void changePassword(String id, ChangePasswordRequest changePasswordRequest) {
        log.info("(changePassword) id: {}", id);

        Customer customer = find(id);

        if (!getPasswordEncoder().matches(changePasswordRequest.getOldPassword(), customer.getPassword())) {
            throw new PasswordIncorrectException();
        }

        customer.setPassword(getPasswordEncoder().encode(changePasswordRequest.getNewPassword()));

        customerRepository.save(customer);
    }

    @Override
    public void equalPassword(String passwordRaw, String passwordEncrypted) {
        if (!getPasswordEncoder().matches(passwordRaw, passwordEncrypted)) {
            throw new PasswordIncorrectException();
          }
    }

    @Override
    @Transactional
    public void active(String id) {
      log.info("(active) id: {}", id);
      Customer customer = find(id);
  
      customerRepository.active(customer.getId());
    }

    @Override
    @Transactional
    public void deactivate(String id) {
        log.info("(deactivate) id: {}", id);
        Customer customer = find(id);
  
        customerRepository.deactivateByEmail(customer.getEmail());
    }

    private void checkExistedEmail(String email) {
        log.info("(checkExistedEmail) email: {}", email);
        if (customerRepository.existsByEmail(email)) {
            log.error("(checkExistedEmail) =============> EmailAlreadyExistException");
            throw new EmailAlreadyExistException();
        }
    }

    private Customer find(String id) {
        log.info("(find) id:{}", id);

        return customerRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }


}
