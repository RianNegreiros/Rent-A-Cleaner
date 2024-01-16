package com.github.riannegreiros.ExpressCleaning.api.assemblers;

import com.github.riannegreiros.ExpressCleaning.api.controllers.ApiDailyPaymentController;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DailyAssembler implements Assembler<DailyResponse> {

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public void addLinks(DailyResponse resource) {
        var id = resource.getId();
        if (securityUtils.isClient() && resource.isNoPayment()) {
            var pagarDailyLink = linkTo(methodOn(ApiDailyPaymentController.class).pay(null, id))
                    .withRel("pay_daily")
                    .withType("POST");

            resource.addLinks(pagarDailyLink);
        }
    }

    @Override
    public void addLinks(List<DailyResponse> collectionResource) {
        collectionResource
                .forEach(this::addLinks);
    }
}