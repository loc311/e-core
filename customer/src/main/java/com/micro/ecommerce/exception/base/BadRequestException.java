package com.micro.ecommerce.exception.base;

import static com.micro.ecommerce.constant.Constant.StatusException.BAD_REQUEST;


public class BadRequestException extends BaseException {
    public BadRequestException() {
      setCode("com.micro.ecommerce.exception.base.BadRequestException");
      setStatus(BAD_REQUEST);
    }
  }
  