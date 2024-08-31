package com.micro.ecommerce.customer.service.auth;

import static com.micro.ecommerce.constant.Constant.AuthenticationConstant.CLAIM_USERNAME_KEY;

import java.util.HashMap;

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
    private final TokenService tokenService;

    @Override
    public LoginResponseDTO login(String email, String password) {

        log.info("(login) start email: {} , password: {}", email, password);

        Customer customer = customerService.getCustomerByEmail(email);
        if (!customer.isActive()) {
            throw new UserNotActivatedException();
        }
        customerService.equalPassword(password, customer.getPassword());

        var claims = new HashMap<String, Object>();
        claims.put(CLAIM_USERNAME_KEY, email);

        String accessToken = tokenService.generateAccessToken(
                customer.getUser_id(), claims);

        String refreshToken = tokenService.generateRefreshToken(
                customer.getUser_id(),
                customer.getEmail());

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    @Override
    public void logout() {
        log.info("(logout)");

        SecurityContextHolder.clearContext();

    }

}
