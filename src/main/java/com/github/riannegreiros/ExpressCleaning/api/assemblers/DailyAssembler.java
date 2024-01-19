package com.github.riannegreiros.ExpressCleaning.api.assemblers;

import com.github.riannegreiros.ExpressCleaning.api.controllers.*;
import com.github.riannegreiros.ExpressCleaning.api.dtos.responses.DailyResponse;
import com.github.riannegreiros.ExpressCleaning.core.repositories.RatingRepository;
import com.github.riannegreiros.ExpressCleaning.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DailyAssembler implements Assembler<DailyResponse> {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void addLinks(DailyResponse resource) {
        var id = resource.getId();
        if (securityUtils.isClient() && resource.isNoPayment()) {
            var pagarDailyLink = linkTo(methodOn(DailyPaymentApiController.class).pay(null, id))
                    .withRel("pay_daily")
                    .withType("POST");

            resource.addLinks(pagarDailyLink);
        }

        if (isAptForPresenceConfirmation(resource)) {
            var confirmPresenceLink = linkTo(methodOn(ConfirmPresenceApiController.class).confirmPresence(id))
                    .withRel("confirm_daily")
                    .withType("PATCH");

            resource.addLinks(confirmPresenceLink);
        }

        if (isAptForRating(resource)) {
            var ratingLink = linkTo(methodOn(RatingApiController.class).rateDaily(null, id))
                    .withRel("rate_daily")
                    .withType("PATCH");
            resource.addLinks(ratingLink);
        }

        if (isAptForCancellation(resource)) {
            var cancelDailyLink = linkTo(methodOn(DailyCancellationApiController.class).cancel(id, null))
                    .withRel("cancel_daily")
                    .withType("PATCH");
            resource.addLinks(cancelDailyLink);
        }

        var selfLink = linkTo(methodOn(DailyApiController.class).findById(id))
                .withSelfRel()
                .withType("GET");

        resource.addLinks(selfLink);
    }

    @Override
    public void addLinks(List<DailyResponse> collectionResource) {
        collectionResource
                .forEach(this::addLinks);
    }

    private boolean isAptForPresenceConfirmation(DailyResponse resource) {
        return resource.isConfirmed()
                && isDateAttendedInThePast(resource)
                && resource.getHousekeeper() != null;
    }

    private boolean isDateAttendedInThePast(DailyResponse resource) {
        return resource.getAttendanceDate().isBefore(LocalDateTime.now());
    }

    private boolean isAptForRating(DailyResponse resource) {
        return resource.isCompleted()
                && !ratingRepository.existsByReviewerAndDailyId(securityUtils.getLoggedUser(), resource.getId());
    }

    private boolean isAptForCancellation(DailyResponse resource) {
        return (resource.isPaid() || resource.isConfirmed())
                && resource.getAttendanceDate().isAfter(LocalDateTime.now());
    }
}