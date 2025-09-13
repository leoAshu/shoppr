package com.leo.shoppr.exception;

import com.leo.shoppr.response.CustomResponse;
import com.leo.shoppr.response.ResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomResponse<Void>>  handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request){
        CustomResponse<Void> response = CustomResponse.<Void>builder()
                .status(ResponseStatus.ERROR)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<Void>>  handleGenericException(Exception ex, HttpServletRequest request){
        CustomResponse<Void> response = CustomResponse.<Void>builder()
                .status(ResponseStatus.ERROR)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
