package org.spring.tutorial.examples.batch.repository;

import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
}
