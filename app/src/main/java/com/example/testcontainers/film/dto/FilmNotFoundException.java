package com.example.testcontainers.film.dto;

public class FilmNotFoundException extends RuntimeException {

  public FilmNotFoundException(String title) {
    super("No film of title " + title + " found", null, false, false);
  }

}
