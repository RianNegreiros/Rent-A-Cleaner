package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.CitiesServedRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CityServedResponse;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.MessageResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.ApiCityServedMapper;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.CityServedNotFoundException;
import com.github.riannegreiros.ExpressCleaning.core.models.CityServed;
import com.github.riannegreiros.ExpressCleaning.core.repositories.CityServedRepository;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import com.github.riannegreiros.ExpressCleaning.core.services.checkcity.adapters.CheckCityService;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesServedApiService {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private ApiCityServedMapper mapper;

    @Autowired
    private CityServedRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckCityService checkCityService;

    public List<CityServedResponse> listCitiesServed() {
        return securityUtils.getLoggedUser()
                .getCitiesServed()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MessageResponse updateCitiesServed(CitiesServedRequest request) {
        var loggedUser = securityUtils.getLoggedUser();
        var citiesServed = new ArrayList<CityServed>();

        request.getCities().forEach(cityServedRequest -> {
            var ibgeCode = cityServedRequest.getIbgeCode();
            CityServed cityServed;
            try {
                cityServed = findCityResponseByIbgeCode(ibgeCode);
            } catch (CityServedNotFoundException exception) {
                cityServed = registerCityServed(ibgeCode);
            }
            citiesServed.add(cityServed);
        });
        loggedUser.setCitiesServed(citiesServed);
        userRepository.save(loggedUser);
        return new MessageResponse("Cities served successfully updated!");
    }

    private CityServed registerCityServed(String ibgeCode) {
        var city = checkCityService.findCityByIbgeCode(ibgeCode);
        var cityServed = new CityServed();
        cityServed.setIbgeCode(ibgeCode);
        cityServed.setCity(city.getCity());
        cityServed.setState(city.getState());

        return repository.save(cityServed);
    }

    private CityServed findCityResponseByIbgeCode(String ibgeCode) {
        var message = String.format("City with ibge code %s not found", ibgeCode);
        return repository.findByIbgeCode(ibgeCode)
                .orElseThrow(() -> new CityServedNotFoundException(message));
    }
}
