package com.micro.product.controller.cart;

import static com.micro.product.constant.Constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.micro.product.constant.Constant.CommonConstants.LANGUAGE;
import static com.micro.product.constant.Constant.MessageException.CREATE_CART_SUCCESS;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.product.dto.ResponseGeneral;
import com.micro.product.dto.request.ProductPurchaseRequest;
import com.micro.product.dto.response.ProductPurchaseResponse;
import com.micro.product.model.Cart;
import com.micro.product.service.CartService;
import com.micro.product.service.MessageService;
import com.micro.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    private final MessageService messageService;

    @PostMapping("/create")
    public ResponseGeneral<List<ProductPurchaseResponse>> addToCart(
            @RequestBody List<ProductPurchaseRequest> productPurchaseRequests,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language) {

        // Pass the productPurchaseRequests to the service layer
        List<ProductPurchaseResponse> productPurchaseResponses = cartService.createCart(productPurchaseRequests);

        // Return the response with the success message and the list of
        // ProductPurchaseResponse
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(CREATE_CART_SUCCESS, language),
                productPurchaseResponses);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAll() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }
}
