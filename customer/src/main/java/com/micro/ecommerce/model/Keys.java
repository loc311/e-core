package com.micro.ecommerce.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Keys")
public class Keys {

    @Id
    private String id;

    @NotNull
    private String customerId;

    @NotNull
    private String privateKey;

    @NotNull
    private String publicKey;

    @NotNull
    private String refreshToken;

    @NotNull
    private String refreshTokenExpiredAt;

    
    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
