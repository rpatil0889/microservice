package com.practice.user_service.exceptions;

import com.practice.user_service.responses.ApiResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

     @ExceptionHandler(DataIntegrityViolationException.class)
     public ApiResponse<String> handleSomeException(DataIntegrityViolationException ex) {
         // Handle the exception and return a custom error response
         return new ApiResponse<>(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal Server Error", null);
     }
}
