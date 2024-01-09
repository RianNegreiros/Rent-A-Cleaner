package com.github.riannegreiros.ExpressCleaning.core.publishers;

import com.github.riannegreiros.ExpressCleaning.core.events.NewUserEvent;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NewUserPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publish(User user) {
        var event = new NewUserEvent(this, user);
        eventPublisher.publishEvent(event);
    }
}
