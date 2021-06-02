package com.app.contactInfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> message = new ArrayList<>();
        ex.getBindingResult().getAllErrors();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            message.add(error.getDefaultMessage());
        });
        ErrorDetails errorDetails = new ErrorDetails(new Date(),message,"fail" );

        return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity contactNotFoundException(ContactNotFoundException ex, WebRequest request) {
        List<String> message = new ArrayList<>();
        message.add(ex.getMessage());
         ErrorDetails errorDetails = new ErrorDetails(new Date(), message,"fail" );

         return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity globleExcpetionHandler(Exception ex, WebRequest request) {
        List<String> message = new ArrayList<>();
        message.add("Server Error");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message,"fail");
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity globleRuntimeExceptionHandler(RuntimeException ex, WebRequest request) {
        List<String> message = new ArrayList<>();
        message.add("Server Error");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message,"fail");
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}