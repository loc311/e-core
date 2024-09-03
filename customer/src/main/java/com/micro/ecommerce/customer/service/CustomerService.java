package com.micro.ecommerce.customer.service;

import com.micro.ecommerce.dto.response.CustomerResponse;
import com.micro.ecommerce.model.Customer;
import com.micro.ecommerce.dto.request.user.ChangePasswordRequest;
import com.micro.ecommerce.dto.request.user.CustomerRequest;
import com.micro.ecommerce.dto.request.user.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    Customer getCustomerByEmail(String email);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(String id, CustomerUpdateRequest customerUpdateRequest);

    CustomerResponse detailCustomer(String id);

    void changePassword(String id, ChangePasswordRequest changePasswordRequest);

    void active(String id);

    void deactivate(String id);

    void equalPassword(String passwordRaw, String passwordEncrypted);

    void deleteCustomer(String id);
}
