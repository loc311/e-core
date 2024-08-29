package com.micro.ecommerce.exception.base;

import static com.micro.ecommerce.constant.Constant.StatusException.INTERNAL_SERVER_ERROR;

public class InternalServerError extends BaseException{
  public InternalServerError() {
    setCode("com.lawman.shop_sport.exception.base.InternalServerError");
    setStatus(INTERNAL_SERVER_ERROR);
  }
}