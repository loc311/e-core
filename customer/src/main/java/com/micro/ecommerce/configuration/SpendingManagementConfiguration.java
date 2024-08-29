package com.micro.ecommerce.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.micro.ecommerce.customer.service.MessageService;
import com.micro.ecommerce.customer.service.impl.MessageServiceImpl;

@Configuration
public class SpendingManagementConfiguration {
    @Bean
    public MessageService messageService(MessageSource messageSource) {
        return new MessageServiceImpl(messageSource);
    }
}
