package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.adapters.AddressService;
import com.github.riannegreiros.ExpressCleaning.core.services.checkaddress.dto.AddressResponse;
import com.github.riannegreiros.ExpressCleaning.core.services.checkcity.adapters.CheckCityService;
import com.github.riannegreiros.ExpressCleaning.core.services.checkcity.dtos.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class ApiAddressController {

    @Autowired
    private AddressService service;

    @Autowired
    private CheckCityService checkCityService;

    @GetMapping
    public AddressResponse getAddressByZipCode(@RequestParam(required = false) String cep) {
        return service.getAddressByZipCode(cep);
    }

    @GetMapping
    public CityResponse findCityByIbgeCode(@PathVariable String ibgeCode) {
        return checkCityService.findCityByIbgeCode(ibgeCode);
    }
}
