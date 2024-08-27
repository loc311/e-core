package com.micro.product.constant;

public class Constant {
    public static class CommonConstants {
      public static final String SUCCESS = "com.micro.product.constant.CommonConstants.SUCCESS";
      public static final String ENCODING_UTF_8 = "UTF-8";
      public static final String LANGUAGE = "Accept-Language";
      public static final String DEFAULT_LANGUAGE = "en";
      public static final String SYSTEM = "SYSTEM";
      public static final Integer EXIST = 1;
      public static final Double DEFAULT_COIN = 0.;
      public static final Integer ACTIVE = 1;
      public static final Integer INACTIVE = 0;
      public static final String ADMIN = "ADMIN";
      public static final String ANONYMOUS = "ANONYMOUS";
  
    }
  
    public static class StatusException {
      public static final Integer NOT_FOUND = 404;
      public static final Integer CONFLICT = 409;
      public static final Integer BAD_REQUEST = 400;
      public static final Integer INTERNAL_SERVER_ERROR = 500;
    }
  
    public static class MessageException {
      public static final String CREATE_TAG_FINANCE_SUCCESS = "com.micro.product.controller.TagFinanceController.create";
      public static final String UPDATE_TAG_FINANCE_SUCCESS = "com.micro.product.controller.TagFinanceController.update";
  
      public static final String CREATE_TRANSACTION_SUCCESS = "com.micro.product.controller.TransactionController.create";
      public static final String UPDATE_TRANSACTION_SUCCESS = "com.micro.product.controller.TransactionController.update";

      public static final String CREATE_PRODUCT_SUCCESS = "com.micro.product.controller.ProductController.create";
      public static final String GET_ONE_PRODUCT_SUCCESS = "com.micro.product.controller.ProductController.findById";

      public static final String CREATE_CART_SUCCESS = "com.micro.product.controller.CartController.create";
      public static final String GET_LIST_PURCHASE_PRODUCTS ="com.micro.product.controller.ProductController.purchaseProducts";
      public static final String UPDATE_PRODUCT_SUCCESS = "com.micro.product.controller.ProductController.update";
      public static final String CHANGE_PASSWORD_SUCCESS = "com.micro.product.controller.ProductController.changePassword";
  
    }
  
    public static class InvalidMessageException {
  
      public static final String INVALID_EMAIL = "com.example.quanlichitieu.validation.invalidEmail";
  
    }
  
    public static class AuthenticationConstant {
      public static final String CLAIM_USERNAME_KEY = "username";
  
      public static final String CLAIM_AUTHORITIES_KEY = "authorities";
    }
  
  
  }
  