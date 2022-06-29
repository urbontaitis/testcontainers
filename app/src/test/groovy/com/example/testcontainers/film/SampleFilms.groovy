package com.example.testcontainers.film

import com.example.testcontainers.film.dto.FilmDto
import com.example.testcontainers.film.dto.FilmTypeDto
import groovy.transform.SelfType
import spock.lang.Specification

@SelfType(Specification)
trait SampleFilms {
  FilmDto rambo = new FilmDto("Rambo", FilmTypeDto.NEW)
  FilmDto commando = new FilmDto("Commando", FilmTypeDto.OLD)
}
