package com.micro.product.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.micro.product.service.MessageService;
import com.micro.product.service.impl.MessageServiceImpl;

@Configuration
public class SpendingManagementConfiguration {
    @Bean
    public MessageService messageService(MessageSource messageSource) {
        return new MessageServiceImpl(messageSource);
    }
}
