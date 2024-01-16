package com.github.riannegreiros.ExpressCleaning.api.assemblers;

import com.github.riannegreiros.ExpressCleaning.api.controllers.ApiDailyController;
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
        }
    }

    @Override
    public void addLinks(List<UserResponse> collectionResource) {}
}
