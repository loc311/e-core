package com.micro.product.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Integer id;

    @NotNull(message = "Product name must be required")
    private String name;

    @NotNull(message = "Product description must be required")
    private String description;

    @NotNull(message = "Product quantity must be required")
    private double quantity;

    @NotNull(message = "Product price must be required")
    private BigDecimal price;

    @NotNull(message = "Product categoryId must be required")
    private Integer categoryId;
}
