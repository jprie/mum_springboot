package com.example.qtacoapp.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IngredientControllerAdvice {

    record Error(String message) {}
    @ExceptionHandler
    ResponseEntity<Error> handleIngredientNotFoundException(IngredientNotFoundException e) {

        return ResponseEntity.badRequest().body(new Error(e.getMessage()));
    }
}
