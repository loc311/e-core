// package com.micro.ecommerce.security;

// import io.jsonwebtoken.*;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import org.springframework.security.core.userdetails.UserDetails;


// import java.util.Date;

// @validateToken
// public class JwtTokenProvider {

//     @Value("${app.jwtSecret}")
//     private String jwtSecret;

//     @Value("${app.jwtExpirationInMs}")
//     private int jwtExpirationInMs;

//     public String generateToken(Authentication authentication) {
//         UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//         Date now = new Date();
//         Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

//         return Jwts.builder()
//         .setSubject(userDetails.getEmail())
//                 .setIssuedAt(new Date())
//                 .setExpiration(expiryDate)
//                 .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                 .compact();
//     }

//     public Long getUserIdFromJWT(String token) {
//         Claims claims = Jwts.parser()
//                 .setSigningKey(jwtSecret)
//                 .parseClaimsJws(token)
//                 .getBody();

//         return Long.parseLong(claims.getSubject());
//     }

//     public boolean validateToken(String authToken) {
//         try {
//             Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//             return true;
//         } catch (SignatureException ex) {
//             // Log error
//         } catch (MalformedJwtException ex) {
//             // Log error
//         } catch (ExpiredJwtException ex) {
//             // Log error
//         } catch (UnsupportedJwtException ex) {
//             // Log error
//         } catch (IllegalArgumentException ex) {
//             // Log error
//         }
//         return false;
//     }
// }