package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByDailyAndReviewer(Daily daily, User reviewer);

    @Query(
            """
            SELECT
                AVG(a.rate)
            FROM
                Rating a
            WHERE
                a.rated = :user
            """
    )
    Double getAverageRating(User user);

    @Query(
            """
            SELECT
                COUNT(*) = 2
            FROM
                Rating a
            WHERE
                a.daily = :daily
            """
    )
    boolean isClientAndCleanerRatedDaily(Daily daily);

    boolean existsByReviewerAndDailyId(User rated, Long dailyId);

    Page<Rating> findByRated(User rated, Pageable pageable);

    default List<Rating> getLastRatings(User rated) {
        var ratingSort = Sort.sort(Rating.class);
        var sort = ratingSort.by(Rating::getCreatedAt).descending();
        var pageable = PageRequest.of(0, 2, sort);
        return findByRated(rated, pageable).getContent();
    }
}
