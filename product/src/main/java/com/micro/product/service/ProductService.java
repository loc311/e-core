package com.micro.product.service;

import com.micro.product.dto.request.ProductPurchaseRequest;
import com.micro.product.dto.request.ProductRequest;
import com.micro.product.dto.response.ProductPurchaseResponse;
import com.micro.product.dto.response.ProductResponse;
import com.micro.product.exception.ProductPurchaseException;
import com.micro.product.mapper.ProductMapper;
import com.micro.product.model.Product;
import com.micro.product.repository.ProductRepository;
import com.micro.product.ultils.MapperUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper productMapper;

   
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {

        // check if product exists
        if (repo.existsById(request.getId())) {
            throw new EntityNotFoundException("Product already exists with Id: " + request.getId());
        }
        Product product = MapperUtils.toEntity(request, Product.class);

        return MapperUtils.toDTO(repo.save(product), ProductResponse.class);
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
