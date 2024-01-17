package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.CityServed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityServedRepository  extends JpaRepository<CityServed, Long> {
    Optional<CityServed> findByIbgeCode(String ibgeCode);
}
