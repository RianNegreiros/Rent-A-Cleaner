package com.github.riannegreiros.ExpressCleaning.core.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.riannegreiros.ExpressCleaning.core.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Page<User> findByServedCitiesIbgeCode(String codigoIbge, Pageable pageable);

  Boolean existsByServedCitiesIbgeCode(String codigoIbge);

  @Query("SELECT count(*) > 0 FROM User u WHERE u.email = :email and (:id is null or u.id != :id)")
  Boolean isEmailRegistered(String email, Long id);
}
