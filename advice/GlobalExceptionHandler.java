package com.practices.demo.advice;

import com.practices.demo.exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;


@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<ApiError>handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiError error=new ApiError(ex.getLocalizedMessage(),HttpStatus .NOT_FOUND);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }//AccountExpierdException//BadCrediantials//CreadinatialsException//sessionException
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError>handleAuthenticationException(AuthenticationException ex){
        ApiError apiError=new ApiError(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException exc){
        ApiError apiError=new ApiError(exc.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);

    }
}
