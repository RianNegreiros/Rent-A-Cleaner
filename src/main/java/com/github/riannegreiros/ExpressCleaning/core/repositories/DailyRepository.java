package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyRepository extends
        JpaRepository<Daily, Long>,
        JpaSpecificationExecutor<Daily> {

    List<Daily> findByClient(User client);
    List<Daily> findByHousekeeper(User housekeeper);

    @Query(
            """
            SELECT
                d
            FROM
                Daily d
            WHERE
                d.status = com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus.PAID
            AND
                d.housekeeper IS NULL
            AND
                d.ibgeCode IN :cities
            AND
                :candidate NOT MEMBER OF d.candidates
            AND
                SIZE(d.candidates) < 3
            """
    )
    List<Daily> findOpportunities(List<String> cities, User candidate);
}
