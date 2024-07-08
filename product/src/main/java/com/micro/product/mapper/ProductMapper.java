package com.micro.product.mapper;

import org.springframework.stereotype.Service;

import com.micro.product.dto.ProductPurchaseResponse;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.model.Category;
import com.micro.product.model.Product;

import jakarta.validation.Valid;

@Service
public class ProductMapper {

    public Product toProduct(@Valid ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .quantity(request.quantity())
                .category(Category
                        .builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity);
    }
}
