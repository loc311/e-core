package com.micro.ecommerce.customer.service;

import java.util.Map;

public interface TokenService {

  String generateAccessToken(String id, Map<String, Object> claims);

  String generateRefreshToken(String id, String email);

  void validateToken(String token);

  // void saveToken(String id, String accessToken, String refreshToken);

  //start authentication

  // void authenticate(String id, String accessToken, String refreshToken);

  // String verifyToken(String token, String publicKey);

  // String verifyRefreshToken(String refreshToken, String privateKey);
  
  //end authentication

  String getSubjectFromToken(String token);

  Long getExpirationTime(String token);

  String getEmailFromToken(String token);
}
