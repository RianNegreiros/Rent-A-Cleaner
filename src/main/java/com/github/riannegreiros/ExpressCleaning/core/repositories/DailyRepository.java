package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRepository extends
        JpaRepository<Daily, Long>,
        JpaSpecificationExecutor<Daily> {
}
