package com.example.testcontainers.film.domain;

import com.example.testcontainers.film.dto.FilmDto;
import com.example.testcontainers.film.dto.FilmTypeDto;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Entity
class Film {
  @Id
  private String title;
  private String type;

  private LocalDate releaseDate;

  protected Film() {}

  protected Film(String title, String type) {
    this.title = title;
    this.type = type;
  }

  FilmDto dto() {
    return new FilmDto(title, FilmTypeDto.valueOf(type));
  }

  public String getTitle() {
    return title;
  }
}
