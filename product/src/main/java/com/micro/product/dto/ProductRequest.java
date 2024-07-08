package com.micro.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(
    
     Integer id,
     @NotNull(message = "Product name much be required")
     String name,
     @NotNull(message = "Product description must be required")
     String description,
     @NotNull(message = "Product quantity must be required")
     double quantity,
     @NotNull(message = "Product price must be required")
     BigDecimal price,
     @NotNull(message = "Product categoryId must be required")
     Integer categoryId
) {

}
