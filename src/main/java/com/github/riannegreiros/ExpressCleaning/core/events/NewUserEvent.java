package com.github.riannegreiros.ExpressCleaning.core.events;

import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.context.ApplicationEvent;


public class NewUserEvent extends ApplicationEvent {

    private User user;

    public NewUserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
