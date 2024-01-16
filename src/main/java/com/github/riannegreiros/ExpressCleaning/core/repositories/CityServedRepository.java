package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.CityServed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityServedRepository  extends JpaRepository<CityServed, Long> {
    Optional<CityServed> findByIbgeCode(String ibgeCode);
}
