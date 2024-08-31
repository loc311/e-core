package com.micro.ecommerce.dto.request.user;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {
    
    @NotBlank
    @Size(min = 6)
    private String newPassword;

    @NotBlank
    @Size(min = 6)
    private String oldPassword;

    @NotBlank
    @Size(min = 6)
    private String confirmPassword;
}
