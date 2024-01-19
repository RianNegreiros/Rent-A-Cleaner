package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.HousekeeperAddressRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HousekeeperAddressResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.HousekeeperAddressApiMapper;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.HousekeeperAddressException;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HousekeeperAddressApiService {
    @Autowired
    private HousekeeperAddressApiMapper mapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public HousekeeperAddressResponse changeAddress(HousekeeperAddressRequest request) {
        var loggedUser = securityUtils.getLoggedUser();

        var address = mapper.toModel(request);
        loggedUser.setAddress(address);

        repository.save(loggedUser);

        return mapper.toResponse(loggedUser.getAddress());
    }

    public HousekeeperAddressResponse showAddress() {
        var loggedUser = securityUtils.getLoggedUser();
        var address = loggedUser.getAddress();

        if (address == null) {
            var message = String.format("User address %s not found", loggedUser.getEmail());
            throw new HousekeeperAddressException(message);
        }
        return mapper.toResponse(address);
    }
}
