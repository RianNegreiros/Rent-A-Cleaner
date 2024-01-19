package com.github.riannegreiros.ExpressCleaning.api.services;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.CleanerAddressRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CleanerAddressResponse;
import com.github.riannegreiros.ExpressCleaning.api.mappers.CleanerAddressApiMapper;
import com.github.riannegreiros.ExpressCleaning.core.exceptions.CleanerAddressException;
import com.github.riannegreiros.ExpressCleaning.core.repositories.UserRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CleanerAddressApiService {
    @Autowired
    private CleanerAddressApiMapper mapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public CleanerAddressResponse changeAddress(CleanerAddressRequest request) {
        var loggedUser = securityUtils.getLoggedUser();

        var address = mapper.toModel(request);
        loggedUser.setAddress(address);

        repository.save(loggedUser);

        return mapper.toResponse(loggedUser.getAddress());
    }

    public CleanerAddressResponse showAddress() {
        var loggedUser = securityUtils.getLoggedUser();
        var address = loggedUser.getAddress();

        if (address == null) {
            var message = String.format("User address %s not found", loggedUser.getEmail());
            throw new CleanerAddressException(message);
        }
        return mapper.toResponse(address);
    }
}
