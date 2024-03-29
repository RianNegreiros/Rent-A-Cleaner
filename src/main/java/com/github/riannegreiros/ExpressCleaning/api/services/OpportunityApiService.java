package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.DailyApiMapper;
import com.github.riannegreiros.ExpressCleaning.core.models.CityServed;
import com.github.riannegreiros.ExpressCleaning.core.repositories.DailyRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityApiService {
    @Autowired
    private DailyRepository repository;

    @Autowired
    private DailyApiMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public List<DailyResponse> searchOpportunities() {
        var loggedUser = securityUtils.getLoggedUser();
        var cities = loggedUser.getCitiesServed()
                .stream()
                .map(CityServed::getIbgeCode)
                .toList();
        return repository.findOpportunities(cities, loggedUser)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
