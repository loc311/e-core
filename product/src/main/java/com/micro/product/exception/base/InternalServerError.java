package com.micro.product.exception.base;

import static com.micro.product.constant.Constant.StatusException.INTERNAL_SERVER_ERROR;

public class InternalServerError extends BaseException{
  public InternalServerError() {
    setCode("com.micro.product.exception.base.InternalServerError");
    setStatus(INTERNAL_SERVER_ERROR);
  }
}