package com.github.riannegreiros.ExpressCleaning.api.controllers;

import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.HateoasResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/api")
public class ApiHomeController {

    @GetMapping
    public HateoasResponse home() {
        var listServicesLink = linkTo(methodOn(ServiceApiController.class).getAll())
                .withRel("list_services")
                .withType("GET");
        var addressZipCodeLink = linkTo(methodOn(AddressApiController.class).getAddressByZipCode(null))
                .withRel("address_cep")
                .expand()
                .withType("GET");
        var housekeeperLocalitiesLink = linkTo(methodOn(HousekeeperApiController.class).getHousekeepersByZipCode(null))
                .withRel("housekeepers_localities")
                .expand()
                .withType("GET");
        var verifyHousekeeperAvailiabilityLInk = linkTo(methodOn(HousekeeperApiController.class).verifyAvailabilityByZipCode(null))
                .withRel("verify_availability_attendance")
                .expand()
                .withType("GET");
        var registerUserLink = linkTo(methodOn(UserApiController.class).register(null))
                .withRel("register_user")
                .withType("POST");
        var loginLink = linkTo(methodOn(AuthApiController.class).authenticate(null))
                .withRel("login")
                .withType("POST");
        var refreshLink = linkTo(methodOn(AuthApiController.class).reAuthenticate(null))
                .withRel("refresh")
                .withType("POST");
        var logoutLink = linkTo(methodOn(AuthApiController.class).logout(null))
                .withRel("logout")
                .withType("POST");
        var loggedUserLink = linkTo(methodOn(MeApiController.class).me())
                .withRel("logged_user")
                .withType("GET");

        var response = new HateoasResponse();
        response.addLinks(
                listServicesLink,
                addressZipCodeLink,
                housekeeperLocalitiesLink,
                verifyHousekeeperAvailiabilityLInk,
                registerUserLink,
                loginLink,
                refreshLink,
                logoutLink,
                loggedUserLink
        );
        return response;
    }

}