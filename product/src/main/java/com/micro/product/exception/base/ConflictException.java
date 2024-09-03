package com.micro.product.exception.base;

import static com.micro.product.constant.Constant.StatusException.CONFLICT;

public class ConflictException extends BaseException {
    public ConflictException(String id, String objectName) {
      setStatus(CONFLICT);
      setCode("com.micro.product.exception.base.ConflictException");
      addParam("id", id);
      addParam("objectName", objectName);
    }
  
    public ConflictException(){
      setStatus(CONFLICT);
      setCode("com.micro.product.exception.base.ConflictException");
    }
  }
  