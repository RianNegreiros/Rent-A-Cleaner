package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HateoasResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class ApiHomeController {

    @GetMapping
    public HateoasResponse home() {
        var listServicesLink = linkTo(methodOn(ApiServiceController.class).getAll())
                .withRel("list_services")
                .withType("GET");
        var addressZipCodeLink = linkTo(methodOn(ApiAddressController.class).getAddressByZipCode(null))
                .withRel("address_zip_code")
                .expand()
                .withType("GET");
        var housekeepersLocalitiesLink = linkTo(methodOn(ApiHousekeeperController.class).getHousekeepersByZipCode(null))
                .withRel("housekeepers_localities")
                .expand()
                .withType("GET");
        var checkAvailabilityLink = linkTo(methodOn(ApiHousekeeperController.class).verifyAvailabilityByZipCode(null))
                .withRel("check_availability_service")
                .expand()
                .withType("GET");

        var response = new HateoasResponse();
        response.addLinks(
                listServicesLink,
                addressZipCodeLink,
                housekeepersLocalitiesLink,
                checkAvailabilityLink
        );
        return response;
    }
}
