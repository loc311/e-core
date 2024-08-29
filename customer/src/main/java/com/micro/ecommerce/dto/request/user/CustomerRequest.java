package com.micro.ecommerce.dto.request.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.micro.ecommerce.model.Address;
import com.micro.ecommerce.validation.ValidationEmail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerRequest {

     private int user_id;

     @ValidationEmail
     private String email;

     @NotBlank
     @Size(min = 6)
     private String password;

     private String firstname;

     private String lastname;

     private Address address;
}
