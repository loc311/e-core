package com.micro.ecommerce.customer.service.impl;

import com.micro.ecommerce.customer.service.TokenService;
import com.micro.ecommerce.exception.token.TokenExpiredException;
import com.micro.ecommerce.exception.token.TokenInvalidException;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import javax.crypto.SecretKey;
import javax.annotation.PostConstruct;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    private Key key;
    @Value("${application.token.expire-time-access-token}")
    private long expireTimeAccessToken;
    @Value("${application.token.expire-time-refresh-token}")
    public long expireTimeRefreshToken;

    @PostConstruct
    public void init() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    
    private String generateToken(int subject, Map<String, Object> claims, long tokenLifeTime) {
        log.info("(generateToken)start");
        return Jwts.builder()
                .setSubject(String.valueOf(subject))
                .claim("claims", claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLifeTime))
                .signWith(key)
                .compact();
    }

    @Override
    public String generateAccessToken(int userId, Map<String, Object> claims) {
        log.info("(generateAccessToken)start");
        return generateToken(userId, claims, expireTimeAccessToken);
    }

    @Override
    public String generateRefreshToken(int userId, String email) {
        log.info("(generateRefreshToken)start");
        var claims = new HashMap<String, Object>();
        claims.put("email", email);
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
        return getClaims(token, key).getExpiration().getTime();
    }

    public String getEmailFromToken(String token) {
        validateToken(token);
        log.info("(getUsernameFromToken) start");
        Map<String, Object> claims = (Map<String, Object>) getClaims(token, key).get("claims");
        return String.valueOf(claims.get("email"));
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
        return getClaims(token, key).getExpiration().before(new Date());
    }

    /**
     * Lấy thông tin ở trong token
     */
    private Claims getClaims(String token, Key key) {
        log.info("(getClaims)");
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * Kiểm tra tính hợp lệ của Token
     */
    public Boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
