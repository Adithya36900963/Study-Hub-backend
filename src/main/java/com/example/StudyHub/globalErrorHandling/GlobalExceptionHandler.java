package com.example.StudyHub.globalErrorHandling;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.StudyHub.model.ErrorModel;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorModel> globalException(RuntimeException ex)
    {
        return ResponseEntity.status(500).body(new ErrorModel(ex.getMessage()));
    }
}
