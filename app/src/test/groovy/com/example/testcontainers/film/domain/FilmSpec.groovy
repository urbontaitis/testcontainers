package com.example.testcontainers.film.domain

import com.example.testcontainers.film.SampleFilms
import com.example.testcontainers.film.dto.FilmDto
import com.example.testcontainers.film.dto.FilmNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class FilmSpec extends Specification implements SampleFilms {

  FilmFacade filmFacade = new FilmConfiguration().filmFacade()

  def "should get film details"() {
    given: "module has film Rambo"
    filmFacade.add(rambo)
    when: "client asks for details of Rambo"
    FilmDto foundFilm = filmFacade.showFilmDetails(rambo.title)
    then: "module returns details of Rambo"
    foundFilm.title == rambo.title
  }

  def "when no film found"() {
    when: "client asks for details of Rambo"
    filmFacade.showFilmDetails(rambo.title)
    then: "module does not return rambo"
    def result = thrown(FilmNotFoundException)
    result.message == "No film of title $rambo.title found"
  }

  def "should get films"() {
    given: "module has two films"
    filmFacade.add(rambo, commando)
    when: "client asks for films"
    Page<FilmDto> foundFilms = filmFacade.findFilms(PageRequest.of(0, 10))
    then: "module returns both films"
    foundFilms.map{ it.title }.sort() == [rambo, commando]*.title.sort()
  }

}
