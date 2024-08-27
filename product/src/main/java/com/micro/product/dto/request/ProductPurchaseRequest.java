package com.micro.product.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//k duoc thi đổi lại record, chỉ có productId, quantity

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequest {
        private Integer productId;
        // private String name;
        // private String description;
        // private BigDecimal price;
        private double quantity;
}
