package org.spring.tutorial.examples.batch.repository;

import org.spring.tutorial.examples.batch.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long>{
}
