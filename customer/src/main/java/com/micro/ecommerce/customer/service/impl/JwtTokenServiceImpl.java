package com.micro.ecommerce.customer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.micro.ecommerce.customer.service.JwtTokenService;
import com.micro.ecommerce.exception.token.TokenExpiredException;
import com.micro.ecommerce.exception.token.TokenInvalidException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtTokenServiceImpl implements JwtTokenService {
  @Value("${application.token.key}")
  private String key;
  @Value("${application.token.expire-time-access-token}")
  private long expireTimeAccessToken;
  @Value("${application.token.expire-time-refresh-token}")
  public long expireTimeRefreshToken;

  private String generateToken(int subject, Map<String, Object> claims, long tokenLifeTime) {
    log.info("(generateToken)start");
    return Jwts.builder()
          .setSubject(String.valueOf(subject))
          .claim("claims", claims)
          .setIssuedAt(new Date(System.currentTimeMillis()))
          .setExpiration(new Date(System.currentTimeMillis() + tokenLifeTime))
          .signWith(SignatureAlgorithm.HS256, key)
          .compact();
  }

  @Override
  public String generateAccessToken(int userId, Map<String, Object> claims) {
    log.info("(generateAccessToken)start");

    return generateToken(userId, claims, expireTimeRefreshToken);

  }

  @Override
  public String generateRefreshToken(int userId, String username) {
    log.info("(generateRefreshToken)start");
    var claims = new HashMap<String, Object>();
    claims.put("username", username);
    return generateToken(userId, claims, expireTimeRefreshToken);
  }

  @Override
  public String getSubjectFromToken(String token) {
    log.info("(getSubjectFromToken)");
    validateToken(token);
    return getClaims(token, key).getSubject();
  }

  @Override
  public Long getExpirationTime(String token) {
    Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    Claims claims = claimsJws.getBody();
    return claims.getExpiration().getTime();
  }

  public String getUsernameFromToken(String token) {
    validateToken(token);
    log.info("(getUsernameFromToken) start");
    Object claimsObj = getClaims(token, key).get("claims");
    
    if (claimsObj instanceof Map) {
        @SuppressWarnings("unchecked")
        Map<String, Object> claims = (Map<String, Object>) claimsObj;
        Object username = claims.get("username");
        return username != null ? username.toString() : null;
    }
    
    log.warn("Claims object is not a Map or username is missing");
    return null;
  }

  public void validateToken(String token) {
    log.info("(validateToken)start");
    if (!isValidToken(token)) {
      log.error("(validateToken) ==========> TokenInvalidException");
      throw new TokenInvalidException();
    }
    if (isExpiredToken(token)) {
      log.error("(validateToken) ==========> TokenExpiredException");
      throw new TokenExpiredException();
    }
  }


  /**
   * Kiểm tra xem token đã hết hạn hay chưa
   */
  public Boolean isExpiredToken(String token) {
    return (Boolean) getClaims(token, key).getExpiration().before(new Date());
  }


  /**
   * Lấy thông tin ở trong token
   */
  private Claims getClaims(String token, String secretKey) {
    log.info("(getClaims)");
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }


  /**
   * Kiểm tra tính hợp lệ của Token
   */
  public Boolean isValidToken(String token) {
    try {
      Jwts.parser().setSigningKey(key).parseClaimsJws(token);
      return (Boolean) true;
    } catch (JwtException | IllegalArgumentException e) {
      return (Boolean) false;
    }
  }

}
