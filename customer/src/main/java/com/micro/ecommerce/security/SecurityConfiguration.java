package com.micro.ecommerce.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.ObjectProvider;

import com.micro.ecommerce.customer.service.CustomerService;
import com.micro.ecommerce.customer.service.TokenService;
import com.micro.ecommerce.security.error.UnAuthenticationCustomHandler;
import com.micro.ecommerce.security.error.UnAuthorizationCustomHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final UnAuthenticationCustomHandler unAuthenticationCustomHandler;
  private final UnAuthorizationCustomHandler unAuthorizationCustomHandler;
  private final ObjectProvider<TokenService> tokenServiceProvider;
  private final ObjectProvider<CustomerService> customerServiceProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/auth/login").permitAll()
            .requestMatchers("/api/v1/customers/register").permitAll()
            .anyRequest().authenticated())
        .exceptionHandling(exc -> exc
            .accessDeniedHandler(unAuthorizationCustomHandler)
            .authenticationEntryPoint(unAuthenticationCustomHandler))
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // phan thêm vào
        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        // phan thêm vào
        .build();
  }

  // phan thêm vào
  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter(
        tokenServiceProvider.getIfAvailable(),
        customerServiceProvider.getIfAvailable());
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
  // phan thêm vào

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedHeader("Authorization");
    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
