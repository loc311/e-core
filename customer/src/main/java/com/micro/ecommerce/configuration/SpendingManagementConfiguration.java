package com.micro.ecommerce.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.micro.ecommerce.customer.service.CustomerService;
import com.micro.ecommerce.customer.service.MessageService;
import com.micro.ecommerce.customer.service.impl.CustomerServiceImpl;
import com.micro.ecommerce.customer.service.impl.MessageServiceImpl;
import com.micro.ecommerce.model.CustomerRepository;

@Configuration
public class SpendingManagementConfiguration {
    @Bean
    public MessageService messageService(MessageSource messageSource) {
        return new MessageServiceImpl(messageSource);
    }

    @Bean
    public CustomerService customerService(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        return new CustomerServiceImpl(repository, passwordEncoder);
    }
}
