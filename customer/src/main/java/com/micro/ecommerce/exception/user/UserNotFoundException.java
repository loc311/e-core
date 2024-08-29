package com.micro.ecommerce.exception.user;

import com.micro.ecommerce.exception.base.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;

public class UserNotFoundException extends NotFoundException {
  public UserNotFoundException() {
    setCode("com.micro.ecommerce.exception.UserNotFoundException");
  }
}
