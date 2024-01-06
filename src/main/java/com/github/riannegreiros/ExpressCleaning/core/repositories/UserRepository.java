package com.github.riannegreiros.ExpressCleaning.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.riannegreiros.ExpressCleaning.core.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  default Boolean isEmailInUse(User user) {
    if (user.getEmail() == null) {
      return false;
    }
    return findByEmail(user.getEmail())
        .map(u -> !u.getId().equals(user.getId()))
        .orElse(false);
  }
}
