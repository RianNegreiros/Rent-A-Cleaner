package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.HousekeeperAddressRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HousekeeperAddressResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.ApiHousekeeperAddressService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.ExpressCleaningPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/address")
public class ApiHousekeeperAddressController {

    @Autowired
    private ApiHousekeeperAddressService service;

    @PutMapping
    @ExpressCleaningPermissions.isHousekeeper
    public HousekeeperAddressResponse changeAddress(@RequestBody @Valid HousekeeperAddressRequest request) {
        return service.changeAddress(request);
    }

    @GetMapping
    @ExpressCleaningPermissions.isHousekeeper
    public HousekeeperAddressResponse showAddress() {
        return service.showAddress();
    }
}
