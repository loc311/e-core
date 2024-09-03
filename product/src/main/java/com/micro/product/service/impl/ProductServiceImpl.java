package com.micro.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.micro.product.dto.request.ProductRequest;
import com.micro.product.dto.response.ProductResponse;
import com.micro.product.exception.product.ProductAlreadyExistException;
import com.micro.product.exception.product.ProductNotFoundException;
import com.micro.product.model.Product;
import com.micro.product.repository.ProductRepository;
import com.micro.product.service.ProductService;
import com.micro.product.ultils.MapperUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        log.info("Creating product: {}", request);
        // check if product exists
        if (repo.existsById(request.getId())) {
            throw new ProductAlreadyExistException();
        }
        Product product = MapperUtils.toEntity(request, Product.class);

        return MapperUtils.toDTO(repo.save(product), ProductResponse.class);
    }

    @Override
    public ProductResponse findById(Integer id) {
        log.info("Finding product by id: {}", id);
        Product product = repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());
        return MapperUtils.toDTO(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> findAll() {
        log.info("Finding all products");
        return repo.findAll()
                .stream()
                .map(product -> MapperUtils.toDTO(product, ProductResponse.class))
                .toList();
    }

}
