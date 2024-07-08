package com.micro.product.controller;

import com.micro.product.dto.ProductPurchaseRequest;
import com.micro.product.dto.ProductPurchaseResponse;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.exception.ProductPurchaseException;
import com.micro.product.mapper.ProductMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper productMapper;

    public Integer createProduct(
            ProductRequest request) {
        var product = productMapper.toProduct(request);
        return repo.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    ) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repo.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products not found");
        }

        var sortedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getQuantity() - productRequest.quantity();
            product.setQuantity(newAvailableQuantity);
            repo.save(product);
            purchasedProducts.add(productMapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repo.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with Id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return repo.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

}
