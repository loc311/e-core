package com.micro.ecommerce.customer.service;

import java.util.Map;


public interface TokenService {
    
   String generateAccessToken(int userId, Map<String, Object> claims);

  String generateRefreshToken(int userId, String email);

  String getSubjectFromToken(String token);

  Long getExpirationTime(String token);

  String getEmailFromToken(String token);
}
