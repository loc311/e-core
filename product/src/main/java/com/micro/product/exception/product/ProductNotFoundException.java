package com.micro.product.exception.product;

import com.micro.product.exception.base.NotFoundException;

public class ProductNotFoundException extends NotFoundException{
    public ProductNotFoundException() { 
        setMessage("Product not found");
    }
}
