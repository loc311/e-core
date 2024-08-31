package com.micro.ecommerce.customer.service.auth;

import com.micro.ecommerce.dto.response.LoginResponseDTO;
import jakarta.servlet.http.HttpServletRequest;


public interface AuthService {
    LoginResponseDTO login(String email, String password);
  
    void logout();
  }
  