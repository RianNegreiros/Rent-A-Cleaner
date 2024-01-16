package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.PaymentRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.validators.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiDailyPaymentService {

    @Autowired
    private DailyRepository dailyRepository;

    @Autowired
    private PaymentValidator validator;

    public MessageResponse pay(PaymentRequest request, Long id) {
        var daily = findDailyPorId(id);

        validator.validate(daily);

        dailyRepository.save(daily);
        return new MessageResponse("Daily rate successfully paid!");
    }

    private Daily findDailyPorId(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return dailyRepository.findById(id)
                .orElseThrow(() -> new DailyNotFoundException(message));
    }
}
