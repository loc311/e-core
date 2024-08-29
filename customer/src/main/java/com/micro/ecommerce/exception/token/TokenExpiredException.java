package com.micro.ecommerce.exception.token;

import com.micro.ecommerce.exception.base.BadRequestException;

public class TokenExpiredException extends BadRequestException {
    public TokenExpiredException() {
        setCode("com.micro.ecommerce.exception.token.TokenExpiredException");
    }
}
