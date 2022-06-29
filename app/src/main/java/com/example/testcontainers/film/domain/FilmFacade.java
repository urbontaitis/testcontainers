package com.example.testcontainers.film.domain;

import com.example.testcontainers.film.dto.FilmDto;
import com.example.testcontainers.film.dto.FilmNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class FilmFacade {

  private final FilmCreator filmCreator;
  private final FilmRepository filmRepository;

  public FilmFacade(FilmCreator filmCreator, FilmRepository filmRepository) {
    this.filmCreator = filmCreator;
    this.filmRepository = filmRepository;
  }

  public void add(FilmDto... dtos) {
    Arrays.stream(dtos)
        .filter(Objects::nonNull)
        .map(filmCreator::create)
        .forEach(filmRepository::save);
  }

  public FilmDto showFilmDetails(String title) {
    return filmRepository.findByTitle(title).map(Film::dto)
        .orElseThrow(() -> new FilmNotFoundException(title));
  }

  public Page<FilmDto> findFilms(Pageable pageable) {
    return filmRepository.findAll(pageable).map(Film::dto);
  }

}
