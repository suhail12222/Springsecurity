package com.practices.demo.advice;

import lombok.Data;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime localDateTime;
 public ApiError(){
    localDateTime=LocalDateTime.now();
  }
  public ApiError( String message,HttpStatus httpStatus ){
      this.httpStatus=httpStatus;
      this.message=message;
   this.localDateTime=LocalDateTime.now();
  }

}

