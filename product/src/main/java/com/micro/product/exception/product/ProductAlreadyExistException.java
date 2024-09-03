package com.micro.product.exception.product;

import com.micro.product.exception.base.ConflictException;

public class ProductAlreadyExistException extends ConflictException {

    public ProductAlreadyExistException(){
        setMessage("Product already exists");
    }
    
}
