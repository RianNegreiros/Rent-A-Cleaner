package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.adapters.AddressService;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.dto.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping
    public AddressResponse getAddressByZipCode(@RequestParam(required = false) String cep) {
        return service.getAddressByZipCode(cep);
    }
}
