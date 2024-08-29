package com.micro.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Customer {

    @Id
    private String id;
    private Integer user_id;
    private String email;
    private String firstname;
    private String lastname;
    private Address address;
    private boolean isActive;
  
}
