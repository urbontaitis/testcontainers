package com.example.testcontainers.film.domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

@Profile("!mongo")
interface FilmRepository extends Repository<Film, String> {

  void save(Film film);
  Optional<Film> findByTitle(String title);
  Page<Film> findAll(Pageable pageable);

}

class InMemoryFilmRepository implements FilmRepository {
  Map<String, Film> store = new ConcurrentHashMap<>();

  @Override
  public void save(Film film) {
    store.put(film.getTitle(), film);
  }

  @Override
  public Optional<Film> findByTitle(String title) {
    return Optional.ofNullable(store.get(title));
  }

  @Override
  public Page<Film> findAll(Pageable pageable) {
    return new PageImpl<>(new ArrayList<>(store.values()));
  }
}
