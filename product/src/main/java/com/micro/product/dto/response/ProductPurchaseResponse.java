package com.micro.product.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseResponse {
        private Integer productId;
        private String name;
        private String description;
        private BigDecimal price;
        private double quantity;
}