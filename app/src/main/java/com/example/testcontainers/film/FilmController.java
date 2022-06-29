package com.example.testcontainers.film;

import com.example.testcontainers.film.domain.FilmFacade;
import com.example.testcontainers.film.dto.FilmDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FilmController {

  private final FilmFacade filmFacade;

  FilmController(FilmFacade filmFacade) {
    this.filmFacade = filmFacade;
  }
  @GetMapping("/film/{title}")
  FilmDto showFilmDetails(@PathVariable String title) {
    return filmFacade.showFilmDetails(title);
  }

  @GetMapping("/film")
  Page<FilmDto> findFilms(Pageable pageable) {
    return filmFacade.findFilms(pageable);
  }

}
