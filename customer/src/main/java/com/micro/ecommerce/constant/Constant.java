package com.micro.ecommerce.constant;

public class Constant {
  public static class CommonConstants {
    public static final String SUCCESS = "com.micro.ecommerce.constant.CommonConstants.SUCCESS";
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
    public static final String CREATE_TAG_FINANCE_SUCCESS = "com.micro.ecommerce.controller.TagFinanceController.create";
    public static final String UPDATE_TAG_FINANCE_SUCCESS = "com.micro.ecommerce.controller.TagFinanceController.update";

    public static final String CREATE_TRANSACTION_SUCCESS = "com.micro.ecommerce.controller.TransactionController.create";
    public static final String UPDATE_TRANSACTION_SUCCESS = "com.micro.ecommerce.controller.TransactionController.update";
    public static final String CREATE_USER_SUCCESS = "com.micro.ecommerce.controller.UserController.create";
    public static final String UPDATE_USER_SUCCESS = "com.micro.ecommerce.controller.UserController.update";
    public static final String CHANGE_PASSWORD_SUCCESS = "com.micro.ecommerce.controller.UserController.changePassword";

  }

  public static class InvalidMessageException {

    public static final String INVALID_EMAIL = "com.micro.ecommerce.validation.invalidEmail";

  }

  public static class AuthenticationConstant {
    public static final String CLAIM_USERNAME_KEY = "username";

    public static final String CLAIM_AUTHORITIES_KEY = "authorities";
  }


}
