package com.micro.ecommerce.customer.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.micro.ecommerce.customer.service.MessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {


    private final MessageSource messageSource;

    @Override
    public String getMessage(String code, String language) {
        try {
            return messageSource.getMessage(code, null, new Locale(language));
        } catch (Exception ex) {
            return code;
        }
    }
}
