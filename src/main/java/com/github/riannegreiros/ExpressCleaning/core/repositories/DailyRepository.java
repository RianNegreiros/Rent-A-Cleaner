package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyRepository extends
        JpaRepository<Daily, Long>,
        JpaSpecificationExecutor<Daily> {

    List<Daily> findByClient(User client);
    List<Daily> findByHousekeeper(User housekeeper);
}
