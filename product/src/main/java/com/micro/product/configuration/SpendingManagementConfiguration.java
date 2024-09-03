package com.micro.product.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.micro.product.repository.CartRepository;
import com.micro.product.repository.ProductRepository;
import com.micro.product.service.CartService;
import com.micro.product.service.MessageService;
import com.micro.product.service.ProductService;
import com.micro.product.service.impl.CartServiceImpl;
import com.micro.product.service.impl.MessageServiceImpl;
import com.micro.product.service.impl.ProductServiceImpl;

@Configuration
public class SpendingManagementConfiguration {
    @Bean
    public MessageService messageService(MessageSource messageSource) {
        return new MessageServiceImpl(messageSource);
    }

    @Bean
    public CartService cartService(CartRepository repository) {
        return new CartServiceImpl(repository);
    }

    @Bean
    public ProductService productService(ProductRepository repository){
        return new ProductServiceImpl(repository);
    }
}
