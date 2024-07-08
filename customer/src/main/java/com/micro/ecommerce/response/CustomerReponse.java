package com.micro.ecommerce.response;

import com.micro.ecommerce.model.Address;

public record CustomerReponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address) {
}
