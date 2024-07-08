package com.micro.ecommerce.model;

import org.springframework.validation.annotation.Validated;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class Address {
    private String street;
    private String houseNum;
    private String zipCode;
}
