package com.practices.demo.advice;

import com.practices.demo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<ApiError>handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiError error=new ApiError(ex.getLocalizedMessage(),HttpStatus .NOT_FOUND);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
