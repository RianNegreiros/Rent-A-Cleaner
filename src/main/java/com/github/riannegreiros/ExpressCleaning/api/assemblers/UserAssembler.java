package com.github.riannegreiros.ExpressCleaning.api.assemblers;

import com.github.riannegreiros.ExpressCleaning.api.controllers.*;
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
            var cadastrarDiariaLink = linkTo(methodOn(DailyApiController.class).register(null))
                    .withRel("register_daily")
                    .withType("POST");

            resource.addLinks(cadastrarDiariaLink);
        } else {
            var updateAddressLink = linkTo(methodOn(CleanerAddressApiController.class).changeAddress(null))
                    .withRel("update_address")
                    .withType("PUT");

            var listarAddressLink = linkTo(methodOn(CleanerAddressApiController.class).showAddress())
                    .withRel("list_address")
                    .withType("GET");

            var updateCitiesLink = linkTo(methodOn(CitiesServedApiController.class).updateCitiesServed(null))
                    .withRel("update_cities")
                    .withType("PUT");

            var citiesServedLink = linkTo(methodOn(CitiesServedApiController.class).listCitiesServed())
                    .withRel("cities_served")
                    .withType("GET");

            var listOpportunitiesLink = linkTo(methodOn(OpportunityApiController.class).searchOpportunities())
                    .withRel("list_opportunities")
                    .withType("GET");

            resource.addLinks(
                    updateAddressLink,
                    listarAddressLink,
                    citiesServedLink,
                    updateCitiesLink,
                    listOpportunitiesLink
            );
        }
            var listDailyLink = linkTo(methodOn(DailyApiController.class).listByLoggedUser())
                    .withRel("list_daily")
                    .withType("GET");

        var updateUserPhotoLink = linkTo(methodOn(UserApiController.class).updateUserPhoto(null))
                .withRel("update_user_photo")
                .withType("POST");

        var updateUserLink = linkTo(methodOn(UserApiController.class).update(null))
                .withRel("update_user")
                .withType("PUT");

            resource.addLinks(listDailyLink, updateUserPhotoLink, updateUserLink);
    }

    @Override
    public void addLinks(List<UserResponse> collectionResource) {}
}
