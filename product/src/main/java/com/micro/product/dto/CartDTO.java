package com.micro.product.dto;

import java.util.List;

import com.micro.product.dto.request.ProductPurchaseRequest;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {

    private Integer id;

    public enum CartState {
        ACTIVE, FAILED, PENDING, COMPLETED
    }

    @Enumerated(EnumType.STRING)
    // @Column(name = "cart_state", nullable = false)
    private CartState cartState = CartState.ACTIVE;

    private List<ProductPurchaseRequest> cartProducts;

    private Integer cartCountProduct;

}
