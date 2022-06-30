package com.example.testcontainers.film.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilmConfiguration {

  FilmFacade filmFacade() {
    return filmFacade(new InMemoryFilmRepository());
  }

  @Bean
  FilmFacade filmFacade(FilmRepository filmRepository) {
    var filmCreator = new FilmCreator();
    return new FilmFacade(filmCreator, filmRepository);
  }
}
