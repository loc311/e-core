package com.micro.product.exception.base;

import static com.micro.product.constant.Constant.StatusException.NOT_FOUND;

public class NotFoundException extends BaseException {
  public NotFoundException(String id, String objectName) {
    setCode("com.micro.product.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
    addParam("id", id);
    addParam("objectName", objectName);
  }
  public NotFoundException(){
    setCode("com.micro.product.exception.base.NotFoundException");
    setStatus(NOT_FOUND);
  }
}
