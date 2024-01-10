package com.github.riannegreiros.ExpressCleaning.core.utils;

import com.github.riannegreiros.ExpressCleaning.core.enums.UserType;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyRateNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.UserNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.DailyRate;
import com.github.riannegreiros.ExpressCleaning.core.models.User;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRateRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DailyRateRepository dailyRateRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Boolean isClient() {
        var authentication = getAuthentication();
        var clientType = UserType.CLIENT.name();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(authority -> authority.getAuthority().equals(clientType));
    }

    public String getEmailFromLoggedUser() {
        return getAuthentication().getName();
    }

    public User getLoggedUser() {
        var email = getEmailFromLoggedUser();
        var message = String.format("email address %s not found", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(message));
    }

    public Boolean isClientFromDailyRate(Long id) {
        var housekeeper = getHousekeeperById(id);
        var userLogged = getLoggedUser();

        if (!userLogged.isClient()) {
            return false;
        }

        return housekeeper.getClient().equals(userLogged);
    }

    public Boolean isClientOrHousekeeperFromDailyRate(Long id) {
        var housekeeper = getHousekeeperById(id);
        var userLogged = getLoggedUser();

        var isClient = housekeeper.getClient() != null && housekeeper.getClient().equals(userLogged);
        var isHousekeeper = housekeeper.getHousekeeper() != null && housekeeper.getHousekeeper().equals(userLogged);

        return isClient || isHousekeeper;
    }

    private DailyRate getHousekeeperById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return dailyRateRepository.findById(id)
                .orElseThrow(() -> new DailyRateNotFoundException(message));
    }
}