package com.micro.product.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.micro.product.service.MessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
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
