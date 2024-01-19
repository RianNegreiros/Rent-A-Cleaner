package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.riannegreiros.ExpressCleaning.core.specifications.DailySpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Repository
public interface DailyRepository extends
        JpaRepository<Daily, Long>,
        JpaSpecificationExecutor<Daily> {

    List<Daily> findByClient(User client);
    List<Daily> findByCleaner(User cleaner);

    default List<Daily> findFiltered(String client, List<DailyStatus> status, Sort sort) {
        return this.findAll(
                where(
                        clientFullNameContains(client)
                                .and(statusIn(status))
                ), sort
        );
    }

    @Query(
            """
            SELECT
                d
            FROM
                Daily d
            WHERE
                d.status = com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus.PAID
            AND
                d.cleaner IS NULL
            AND
                d.ibgeCode IN :cities
            AND
                :candidate NOT MEMBER OF d.candidates
            AND
                SIZE(d.candidates) < 3
            """
    )
    List<Daily> findOpportunities(List<String> cities, User candidate);

    default List<Daily> getAppropriateForSelectionOfDailyCleaner() {
        return this.findAll(
                where(
                        isPaid()
                                .and(withoutCleaner())
                                .and(withCandidatesNumberEqualsTo(3))
                ).or(
                        isPaid()
                                .and(withoutCleaner())
                                .and(withMore24HoursSinceCreation())
                                .and(withCandidatesNumberLessThan(3))
                                .and(withCandidatesNumberBiggerOrEqualsTo(1))
                )
        );
    }

    default List<Daily> getAbleToCancel() {
        return this.findAll(
                where(
                        isPaid()
                                .and(withFewer24HoursForService())
                                .and(withoutCandidates())
                ).or(
                        isWithoutPayment()
                                .and(withMore24HoursSinceCreation())
                                .and(withoutCandidates())
                )
        );
    }
}
