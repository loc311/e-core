package com.micro.ecommerce.exception.base;

import static com.micro.ecommerce.constant.Constant.StatusException.NOT_FOUND;

public class NotFoundException extends BaseException {
  public NotFoundException(String id, String objectName) {
    setCode("com.micro.ecommerce.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
    addParam("id", id);
    addParam("objectName", objectName);
  }
  public NotFoundException(){
    setCode("com.micro.ecommerce.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
  }
}
