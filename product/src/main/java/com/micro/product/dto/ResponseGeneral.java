package com.micro.product.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.micro.product.ultils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of") // ProductRequest.of(...); khong co thi new ProductRequest(...)
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseGeneral<T> {

    private int status;
    private String message;
    private T data;
    private String timestamp;

    public static <T> ResponseGeneral<T> of(int status, String message, T data) {
        return of(status, message, data, DateUtils.getCurrentDateString());
      }
    
      public static <T> ResponseGeneral<T> ofSuccess(String message, T data) {
        return of(HttpStatus.OK.value(), message, data, DateUtils.getCurrentDateString());
      }
    
    
      public static <T> ResponseGeneral<T> ofSuccess(String message) {
        return of(HttpStatus.OK.value(), message, null, DateUtils.getCurrentDateString());
      }
    
      public static <T> ResponseGeneral<T> ofCreated(String message, T data) {
        return of(HttpStatus.CREATED.value(), message, data, DateUtils.getCurrentDateString());
      }
}
