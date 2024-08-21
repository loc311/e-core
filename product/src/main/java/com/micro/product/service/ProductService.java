package com.micro.product.service;

import com.micro.product.controller.ProductRepository;
import com.micro.product.dto.request.ProductPurchaseRequest;
import com.micro.product.dto.request.ProductRequest;
import com.micro.product.dto.response.ProductPurchaseResponse;
import com.micro.product.dto.response.ProductResponse;
import com.micro.product.exception.ProductPurchaseException;
import com.micro.product.mapper.ProductMapper;
import com.micro.product.model.Product;
import com.micro.product.ultils.MapperUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper productMapper;

    // public Integer createProduct(
    // ProductRequest request) {
    // var product = productMapper.toProduct(request);
    // return repo.save(product).getId();
    // }
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {

        //check if product exists
        if (repo.existsById(request.getId())) {
            throw new EntityNotFoundException("Product already exists with Id: " + request.getId());
        }
        Product product = MapperUtils.toEntity(request, Product.class); 

        return MapperUtils.toDTO(repo.save(product), ProductResponse.class);
    }

   

    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request) {
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
                throw new ProductPurchaseException(
                        "Insufficient stock quantity for product with ID:: " + productRequest.productId());
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
