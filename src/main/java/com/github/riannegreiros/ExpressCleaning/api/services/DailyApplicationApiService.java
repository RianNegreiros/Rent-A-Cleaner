package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import com.github.riannegreiros.ExpressCleaning.core.validators.ApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyApplicationApiService {
    @Autowired
    private DailyRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ApplicationValidator validator;

    public MessageResponse apply(Long id) {
        var daily = findDailyById(id);
        validator.validate(daily);
        var userLogged = securityUtils.getLoggedUser();
        daily.getCandidates().add(userLogged);
        repository.save(daily);
        return new MessageResponse("Successful application!");
    }

    private Daily findDailyById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return repository.findById(id)
                .orElseThrow(() -> new DailyNotFoundException(message));
    }
}
