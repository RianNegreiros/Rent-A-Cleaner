package com.github.riannegreiros.ExpressCleaning.api.assemblers;

import com.github.riannegreiros.ExpressCleaning.api.controllers.ApiApplicationController;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OpportunityAssembler implements Assembler<DailyResponse> {
    @Override
    public void addLinks(DailyResponse resource) {
        var id = resource.getId();

        var candidatarDiariaLink = linkTo(methodOn(ApiApplicationController.class).apply(id))
                .withRel("daily_application")
                .withType("POST");

        resource.addLinks(candidatarDiariaLink);
    }

    @Override
    public void addLinks(List<DailyResponse> collectionResource) {
        collectionResource
                .forEach(this::addLinks);
    }
}
