package com.micro.ecommerce.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LoginResponseDTO {
    private String accessToken;
    private String refreshToken;
  
    public static LoginResponseDTO from(String accessToken, String refreshToken) {
      var authSignInResponse = new LoginResponseDTO();
      authSignInResponse.accessToken = accessToken;
      authSignInResponse.refreshToken = refreshToken;
      return authSignInResponse;
    }
}
