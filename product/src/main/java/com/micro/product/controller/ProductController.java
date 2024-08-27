package com.micro.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.product.dto.ResponseGeneral;
import com.micro.product.dto.request.ProductPurchaseRequest;
import com.micro.product.dto.request.ProductRequest;
import com.micro.product.dto.response.ProductPurchaseResponse;
import com.micro.product.dto.response.ProductResponse;
import com.micro.product.model.Cart;
import com.micro.product.service.CartService;
import com.micro.product.service.MessageService;
import com.micro.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.micro.product.constant.Constant.CommonConstants.*;
import static com.micro.product.constant.Constant.MessageException.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final MessageService messageService;
    private final CartService cartService;

    // @Validated(CreateUser.class) ở controller định tên gr valid, @NotNull(groups
    // = CreateUser.class) xác định ở model
    @PostMapping("/create")
    public ResponseGeneral<ProductResponse> create(
            @RequestBody @Valid ProductRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(create) request: {}", request);

        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_PRODUCT_SUCCESS, language),
                productService.createProduct(request));
    }

    @GetMapping("/{product-id}")
    public ResponseGeneral<ProductResponse> findById(
            @PathVariable("product-id") Integer productId,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {
        log.info("(findById) productId: {}", productId);
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(GET_ONE_PRODUCT_SUCCESS, language),
                productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
