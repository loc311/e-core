package com.micro.ecommerce.exception.token;

import com.micro.ecommerce.exception.base.BadRequestException;


public class TokenInvalidException extends BadRequestException {
  public TokenInvalidException() {
    setCode("com.micro.ecommerce.exception.token.TokenInvalidException");
  }
}
