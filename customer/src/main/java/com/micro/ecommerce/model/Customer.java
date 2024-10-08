package com.micro.ecommerce.model;

import lombok.*;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
    private String password;
    private String firstname;
    private String lastname;
    private Address address;
    private boolean isActive;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
  
}
