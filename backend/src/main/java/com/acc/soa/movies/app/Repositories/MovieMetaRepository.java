package com.acc.soa.movies.app.Repositories;

import com.acc.soa.movies.app.Entities.MovieMeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieMetaRepository extends CrudRepository<MovieMeta, Integer> {

}
