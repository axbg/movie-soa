package com.acc.soa.movies.app.Repositories;

import com.acc.soa.movies.app.Entities.UserMeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMetaRepository extends CrudRepository<UserMeta, Integer> {
    Optional<UserMeta> findByUsernameAndPassword(String username, String password);
}
