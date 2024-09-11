package com.micro.ecommerce.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.micro.ecommerce.customer.service.CustomerService;
import com.micro.ecommerce.customer.service.TokenService;
import com.micro.ecommerce.exception.token.TokenExpiredException;
import com.micro.ecommerce.exception.token.TokenInvalidException;
import com.micro.ecommerce.model.Customer;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private CustomerService customerService;

    private TokenService tokenService;

    public JwtAuthenticationFilter(TokenService tokenService, CustomerService customerService) {
        this.tokenService = tokenService;
        this.customerService = customerService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/api/v1/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = getJwtFromRequest(request);

            if (StringUtils.hasText(token)) {
                try {
                    tokenService.validateToken(token);
                    String email = tokenService.getEmailFromToken(token);
                    log.info("Email: {}", email);

                    Customer customer = customerService.getCustomerByEmail(email);
                    if (customer != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                customer, null, Collections.emptyList()); // Sử dụng Collections.emptyList() cho
                                                                          // authorities
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (TokenInvalidException | TokenExpiredException e) {
                    log.error("Token validation failed: {}", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}