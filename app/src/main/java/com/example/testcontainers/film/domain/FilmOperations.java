package com.example.testcontainers.film.domain;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

@Profile("mongo")
@Component
class FilmOperations implements FilmRepository {

  private final MongoOperations mongoOperations;
  private final MongoTemplate mongoTemplate;

  FilmOperations(MongoOperations mongoOperations, MongoTemplate mongoTemplate) {
    this.mongoOperations = mongoOperations;
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void save(Film film) {
    mongoOperations.save(film);
  }

  @Override
  public Optional<Film> findByTitle(String title) {
    var query = new Query(where("title").is(title));
    var result = mongoOperations.find(query, Film.class).get(0);
    return Optional.ofNullable(result);
  }

  @Override
  public Page<Film> findAll(Pageable pageable) {
    var query = new Query().with(pageable);
    var results = mongoOperations.findAll(Film.class);
    return PageableExecutionUtils.getPage(
        results,
        pageable,
        () -> mongoTemplate.count(query.skip(0).limit(0), Film.class)
    );
  }
}
