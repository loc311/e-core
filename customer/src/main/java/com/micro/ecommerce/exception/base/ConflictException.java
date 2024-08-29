package com.micro.ecommerce.exception.base;

import static com.micro.ecommerce.constant.Constant.StatusException.CONFLICT;

public class ConflictException extends BaseException {
    public ConflictException(String id, String objectName) {
      setStatus(CONFLICT);
      setCode("com.lawman.shop_sport.exception.base.ConflictException");
      addParam("id", id);
      addParam("objectName", objectName);
    }
  
    public ConflictException(){
      setStatus(CONFLICT);
      setCode("com.lawman.shop_sport.exception.base.ConflictException");
    }
  }
  