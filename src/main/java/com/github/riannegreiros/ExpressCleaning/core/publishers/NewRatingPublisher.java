package com.github.riannegreiros.ExpressCleaning.core.publishers;

import com.github.riannegreiros.ExpressCleaning.core.events.NewRatingEvent;
import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NewRatingPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(Rating rating) {
        var event = new NewRatingEvent(this, rating);
        applicationEventPublisher.publishEvent(event);
    }
}
