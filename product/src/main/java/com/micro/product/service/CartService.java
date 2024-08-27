package com.micro.product.service;

import java.util.List;

import com.micro.product.dto.CartDTO;
import com.micro.product.dto.request.ProductPurchaseRequest;
import com.micro.product.dto.response.ProductPurchaseResponse;
import com.micro.product.model.Cart;

public interface CartService {

    List<ProductPurchaseResponse> createCart(List<ProductPurchaseRequest> productPurchaseRequests);

    List<Cart> getAllCarts();
    // List<ProductPurchaseResponse> addToCart(List<ProductPurchaseRequest>
    // request);
}
