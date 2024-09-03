package com.micro.product.exception.base;

import static com.micro.product.constant.Constant.StatusException.BAD_REQUEST;


public class BadRequestException extends BaseException {
    public BadRequestException() {
      setCode("com.micro.product.exception.base.BadRequestException");
      setStatus(BAD_REQUEST);
    }
  }
  