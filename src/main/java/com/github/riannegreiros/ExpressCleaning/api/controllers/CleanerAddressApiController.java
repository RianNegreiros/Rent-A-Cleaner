package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.requests.CleanerAddressRequest;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.CleanerAddressResponse;
import com.github.riannegreiros.ExpressCleaning.api.services.CleanerAddressApiService;
import com.github.riannegreiros.ExpressCleaning.core.permissions.RentACleanerPermissions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/address")
public class CleanerAddressApiController {

    @Autowired
    private CleanerAddressApiService service;

    @PutMapping
    @RentACleanerPermissions.isCleaner
    public CleanerAddressResponse changeAddress(@RequestBody @Valid CleanerAddressRequest request) {
        return service.changeAddress(request);
    }

    @GetMapping
    @RentACleanerPermissions.isCleaner
    public CleanerAddressResponse showAddress() {
        return service.showAddress();
    }
}
