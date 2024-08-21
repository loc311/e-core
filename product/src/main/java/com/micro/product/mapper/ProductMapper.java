package com.micro.product.mapper;

import org.springframework.stereotype.Service;

import com.micro.product.dto.request.ProductRequest;
import com.micro.product.dto.response.ProductPurchaseResponse;
import com.micro.product.dto.response.ProductResponse;
import com.micro.product.model.Category;
import com.micro.product.model.Product;

import jakarta.validation.Valid;

@Service
public class ProductMapper {

    public Product toProduct(@Valid ProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(Category
                        .builder()
                        .id(request.getCategoryId())
                        .build())
                .build();
    }

    public String toCustomString(ProductRequest request) {
        return "{" +
                "id=" + request.getId() +
                ", name='" + request.getName() + '\'' +
                ", description='" + request.getDescription() + '\'' +
                ", quantity=" + request.getQuantity() +
                ", price=" + request.getPrice() +
                ", categoryId=" + request.getCategoryId() +
                '}';
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
