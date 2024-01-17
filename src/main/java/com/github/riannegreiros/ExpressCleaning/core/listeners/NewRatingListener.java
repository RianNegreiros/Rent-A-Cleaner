package com.github.riannegreiros.ExpressCleaning.core.listeners;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.events.NewRatingEvent;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.RatingRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewRatingListener {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DailyRepository dailyRepository;

    @EventListener
    public void handleNewRatingEvent(NewRatingEvent event) {
        var rating = event.getRating();
        updateReputationRated(rating);
        updateDailyStatusRated(rating);
    }

    private void updateDailyStatusRated(Rating rating) {
        var daily = rating.getDaily();
        if (ratingRepository.isClientAndHousekeeperRatedDaily(daily)) {
            daily.setStatus(DailyStatus.REVIEWED);
            dailyRepository.save(daily);
        }
    }

    private void updateReputationRated(Rating rating) {
        var rated = rating.getRated();
        var notaMedia = ratingRepository.getAverageRating(rated);
        rated.setReputation(notaMedia);
        userRepository.save(rated);
    }
}
