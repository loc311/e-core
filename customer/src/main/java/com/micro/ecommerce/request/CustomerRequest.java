package com.micro.ecommerce.request;

import com.micro.ecommerce.model.Address;

import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
     String id,
     @NotNull(message = "Firstname cannot be null")
     String firstname,
     @NotNull(message = "lastname cannot be null")
     String lastname,
     @NotNull(message = "email cannot be null")
     String email,
     @NotNull(message = "address cannot be null")
     Address address
) {

}
