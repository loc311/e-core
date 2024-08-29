package com.micro.ecommerce.exception.user;

import com.micro.ecommerce.exception.base.ConflictException;

public class UserAlreadyExistException extends ConflictException {
  public UserAlreadyExistException() {
    setCode("com.micro.ecommerce.exception.UserAlreadyExistException");
  }
}
