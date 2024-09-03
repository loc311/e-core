package com.micro.product.service;

import java.util.List;

import com.micro.product.dto.request.ProductRequest;
import com.micro.product.dto.response.ProductResponse;


public interface ProductService {

   ProductResponse createProduct(ProductRequest request);

   ProductResponse findById(Integer id);

   List<ProductResponse> findAll();
    

}
