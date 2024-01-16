package com.github.riannegreiros.ExpressCleaning.api.assemblers;

import com.github.riannegreiros.ExpressCleaning.api.controllers.ApiCitiesServedController;
import com.github.riannegreiros.ExpressCleaning.api.controllers.ApiDailyController;
import com.github.riannegreiros.ExpressCleaning.api.controllers.ApiHousekeeperAddressController;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements Assembler<UserResponse> {
    @Override
    public void addLinks(UserResponse resource) {
        if (resource.isClient()) {
            var cadastrarDiariaLink = linkTo(methodOn(ApiDailyController.class).register(null))
                    .withRel("register_daily")
                    .withType("POST");

            resource.addLinks(cadastrarDiariaLink);
        } else {
            var updateAddressLink = linkTo(methodOn(ApiHousekeeperAddressController.class).changeAddress(null))
                    .withRel("update_address")
                    .withType("PUT");

            var listarAddressLink = linkTo(methodOn(ApiHousekeeperAddressController.class).showAddress())
                    .withRel("list_address")
                    .withType("GET");

            var updateCitiesLink = linkTo(methodOn(ApiCitiesServedController.class).updateCitiesServed(null))
                    .withRel("update_cities")
                    .withType("PUT");

            var citiesServedLink = linkTo(methodOn(ApiCitiesServedController.class).listCitiesServed())
                    .withRel("cities_served")
                    .withType("GET");


            resource.addLinks(
                    updateAddressLink,
                    listarAddressLink,
                    citiesServedLink,
                    updateCitiesLink
            );
        }
            var listDailyLink = linkTo(methodOn(ApiDailyController.class).listByLoggedUser())
                    .withRel("list_daily")
                    .withType("GET");

            resource.addLinks(listDailyLink);
    }

    @Override
    public void addLinks(List<UserResponse> collectionResource) {}
}
