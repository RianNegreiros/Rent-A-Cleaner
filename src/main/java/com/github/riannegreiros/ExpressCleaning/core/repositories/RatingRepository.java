package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByDailyAndReviewer(Daily daily, User reviewer);
}
