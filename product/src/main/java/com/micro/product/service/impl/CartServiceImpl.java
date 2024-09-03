package com.micro.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.product.dto.request.ProductPurchaseRequest;
import com.micro.product.dto.response.ProductPurchaseResponse;
import com.micro.product.model.Cart;
import com.micro.product.model.Cart.CartState;
import com.micro.product.model.Product;
import com.micro.product.repository.CartRepository;
import com.micro.product.repository.ProductRepository;
import com.micro.product.service.CartService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepository;

    // private ProductRepository productRepository;

    @Override
    public List<ProductPurchaseResponse> createCart(List<ProductPurchaseRequest> productPurchaseRequests) {

        // Create a new Cart entity and set its initial state
        Cart cart = new Cart();
        cart.setCartState(CartState.ACTIVE);

        // List to store the responses
        List<ProductPurchaseResponse> productResponses = new ArrayList<>();

        for (ProductPurchaseRequest request : productPurchaseRequests) {
            Integer productId = request.getProductId();
            Optional<Product> optionalProduct = productRepository.findById(productId); // Fetch product by ID

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                ProductPurchaseResponse response = ProductPurchaseResponse.builder()
                        .productId(productId)
                        .name(product.getName()) // Fetch from Product entity
                        .description(product.getDescription()) // Fetch from Product entity
                        .price(product.getPrice()) // Fetch from Product entity
                        .quantity(request.getQuantity())
                        .build();

                // Add the response to the list
                productResponses.add(response);
            } else {
                // Handle case where productId does not exist, e.g., log error or throw
                // exception
                // Example: throw new ProductNotFoundException("Product with ID " + productId +
                // " not found");
                throw new EntityNotFoundException("Product with ID " + productId + " not found");
            }
        }

        // Set the product IDs and total quantity in the Cart entity
        Integer[] productIds = productResponses.stream()
                .map(ProductPurchaseResponse::getProductId)
                .toArray(Integer[]::new);
        cart.setCart_products(productIds); // Ensure method name matches the field in Cart

        int totalQuantity = productResponses.stream()
                .mapToInt(response -> (int) response.getQuantity())
                .sum();
        cart.setCart_count_product(totalQuantity); // Ensure method name matches the field in Cart

        // Save the Cart entity to the repository
        cartRepo.save(cart);

        // Return the list of ProductPurchaseResponse
        return productResponses;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

}
