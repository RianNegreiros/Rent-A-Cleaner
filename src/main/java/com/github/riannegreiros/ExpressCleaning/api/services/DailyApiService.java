package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.DailyRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.ApiDailyMapper;
import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.DailyNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import com.github.riannegreiros.ExpressCleaning.core.validators.DailyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DailyApiService {
    @Autowired
    private DailyRepository repository;

    @Autowired
    private ApiDailyMapper mapper;

    @Autowired
    private DailyValidator validator;

    @Autowired
    private SecurityUtils securityUtils;

    public DailyResponse register(DailyRequest request) {
        var model = mapper.toModel(request);

        model.setCommissionValue(calculateCommission(model));
        model.setClient(securityUtils.getLoggedUser());
        model.setStatus(DailyStatus.NO_PAYMENT);

        validator.validate(model);

        var registeredModel = repository.save(model);

        return mapper.toResponse(registeredModel);
    }

    public List<DailyResponse> listByLoggedUser() {
        var loggedUser = securityUtils.getLoggedUser();

        List<Daily> dailyRates;

        if (loggedUser.isClient()) {
            dailyRates = repository.findByClient(loggedUser);
        } else {
            dailyRates = repository.findByHousekeeper(loggedUser);
        }

        return dailyRates.stream()
                .map(mapper::toResponse)
                .toList();
    }

    public DailyResponse findById(Long id) {
        var daily = findDailyById(id);

        return mapper.toResponse(daily);
    }

    private Daily findDailyById(Long id) {
        var message = String.format("Daily with id %d not found", id);
        return repository.findById(id)
                .orElseThrow(() -> new DailyNotFoundException(message));
    }

    private BigDecimal calculateCommission(Daily model) {
        var service = model.getService();
        var price = model.getPrice();
        var percentageCommission = service.getPercentageCommission();
        var bigDecimal100 = new BigDecimal(100);

        return price.multiply(percentageCommission.divide(bigDecimal100)).setScale(2);
    }
}
