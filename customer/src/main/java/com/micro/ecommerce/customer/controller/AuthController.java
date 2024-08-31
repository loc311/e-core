package com.micro.ecommerce.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ecommerce.customer.service.MessageService;
import com.micro.ecommerce.customer.service.auth.AuthService;
import com.micro.ecommerce.dto.ResponseGeneral;
import com.micro.ecommerce.dto.request.LoginRequestDTO;
import com.micro.ecommerce.dto.response.LoginResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.micro.ecommerce.constant.Constant.CommonConstants.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final MessageService messageService;

    @PostMapping("/login")
    public ResponseGeneral<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(login) start");

        return ResponseGeneral.of(
                HttpStatus.OK.value(),
                messageService.getMessage(SUCCESS, language),
                authService.login(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseGeneral<Void> logout(
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {

        log.info("(logout)");
        authService.logout();
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(SUCCESS, language));
    }
}
