package com.example.testcontainers.film.domain;

import com.example.testcontainers.film.dto.FilmDto;

class FilmCreator {

  Film create(FilmDto dto) {
    return new Film(dto.title(), dto.type().name());
  }

}
