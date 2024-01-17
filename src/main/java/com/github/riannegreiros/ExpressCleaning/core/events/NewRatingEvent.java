package com.github.riannegreiros.ExpressCleaning.core.events;

import com.github.riannegreiros.ExpressCleaning.core.models.Rating;
import org.springframework.context.ApplicationEvent;

public class NewRatingEvent extends ApplicationEvent {
    private final Rating rating;

    public NewRatingEvent(Object source, Rating rating) {
        super(source);
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }
}
