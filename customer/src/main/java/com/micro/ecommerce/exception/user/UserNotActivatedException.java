package com.micro.ecommerce.exception.user;

import com.micro.ecommerce.exception.base.ConflictException;

public class UserNotActivatedException extends ConflictException {
  public UserNotActivatedException() {
    setCode("com.micro.ecommerce.exception.UserNotActiveException");
  }
}
