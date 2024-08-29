package com.micro.ecommerce.exception.user;

import com.micro.ecommerce.exception.base.ConflictException;

public class EmailAlreadyExistException extends ConflictException {
  public EmailAlreadyExistException(){
    setCode("com.micro.ecommerce.exception.EmailAlreadyExistException");
  }
}
