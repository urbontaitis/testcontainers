package com.example.testcontainers.infrastructure;

import com.example.testcontainers.film.dto.FilmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingAdvice {

  @ExceptionHandler(FilmNotFoundException.class)
  ResponseEntity<ErrorMessage> handleNotFoundFilms(FilmNotFoundException e) {
    ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), null);
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }

  record ErrorMessage(String message, String details) {}

}
