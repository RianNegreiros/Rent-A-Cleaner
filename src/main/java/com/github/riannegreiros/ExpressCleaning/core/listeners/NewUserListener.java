package com.github.riannegreiros.ExpressCleaning.core.listeners;

import com.github.riannegreiros.ExpressCleaning.core.events.NewUserEvent;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.services.email.adapters.EmailService;
import com.github.riannegreiros.ExpressCleaning.core.services.email.dtos.EmailParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class NewUserListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    public void handleNewUserEvent(NewUserEvent event) {
        var user = event.getUser();

        if (user.isClient() || user.isHousekeeper()) {
            var emailParams = createEmailParams(user);
        }
    }

    private EmailParams createEmailParams(User user) {
        var props = createEmailProps(user);
        var emailParams = new EmailParams();
        emailParams.setDestination(user.getEmail());
        emailParams.setSubject("Registration successful");
        emailParams.setTemplate("emails/welcome");
        emailParams.setProps(props);
        return emailParams;
    }

    private HashMap<String, Object> createEmailProps(User user) {
        var props = new HashMap<String, Object>();
        props.put("name", user.getFullName());
        props.put("userType", user.getUserType().name());
        return props;
    }
}
