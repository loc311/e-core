package com.micro.ecommerce.customer.service.auth;

import static com.micro.ecommerce.constant.Constant.AuthenticationConstant.CLAIM_USERNAME_KEY;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.micro.ecommerce.customer.service.CustomerService;
import com.micro.ecommerce.customer.service.TokenService;
import com.micro.ecommerce.dto.response.LoginResponseDTO;
import com.micro.ecommerce.exception.user.UserNotActivatedException;
import com.micro.ecommerce.model.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public LoginResponseDTO login(String email, String password) {

       log.info("(login) start email: {}", email);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Customer customer = (Customer) authentication.getPrincipal();

        if (!customer.isActive()) {
            throw new UserNotActivatedException();
        }

        var claims = new HashMap<String, Object>();
        claims.put(CLAIM_USERNAME_KEY, email);

        String accessToken = tokenService.generateAccessToken(
                customer.getId(), claims);

        String refreshToken = tokenService.generateRefreshToken(
                customer.getId(),
                customer.getEmail());

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    @Override
    public void logout() {
        log.info("(logout)");

        SecurityContextHolder.clearContext();

    }

}
