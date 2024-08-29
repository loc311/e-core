package com.micro.ecommerce.exception.user;

import com.micro.ecommerce.exception.base.ConflictException;

public class PasswordIncorrectException extends ConflictException {
  public PasswordIncorrectException(){
    setCode("com.micro.ecommerce.exception.PasswordIncorrectException");
  }
}
