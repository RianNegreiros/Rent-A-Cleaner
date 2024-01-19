package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.validators.ConfirmPresenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmPresenceApiService {
    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private ConfirmPresenceValidator validator;

    public MessageResponse confirmPresence(Long id) {
        var daily = searchDailyById(id);
        validator.validate(daily);
        daily.setStatus(DailyStatus.COMPLETED);
        dailyRepository.save(daily);
        return new MessageResponse("Attendance successfully confirmed!");
    }

    private Daily searchDailyById(Long id) {
        var message = String.format("Daily id %d not found", id);
        return dailyRepository.findById(id)
                .orElseThrow(() -> new DailyNotFoundException(message));
    }
}
