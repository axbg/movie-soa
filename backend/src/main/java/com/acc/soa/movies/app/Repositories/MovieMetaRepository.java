package com.acc.soa.movies.app.Repositories;

import com.acc.soa.movies.app.Entities.MovieMeta;
import com.acc.soa.movies.app.Entities.UserMeta;
import com.acc.soa.movies.app.SOAPEntities.Movies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieMetaRepository extends CrudRepository<MovieMeta, Integer> {

    Optional<MovieMeta> findByTnmbAndOwner(Integer tnmb, UserMeta user);
    List<MovieMeta> findAllByOwner(UserMeta user);
}
