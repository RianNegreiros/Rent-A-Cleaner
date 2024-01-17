package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OpportunityResponse extends DailyResponse {
    private List<RatingResponse> clientRatings;

    public OpportunityResponse() {
    }

    public OpportunityResponse(List<RatingResponse> clientRatings) {
        this.clientRatings = clientRatings;
    }

    public OpportunityResponse(Long id, Integer status, String cancellationReason, String serviceName, LocalDateTime attendanceDate, Integer attendanceTime, BigDecimal price, String street, String number, String neighborhood, String complement, String city, String state, String zipCode, String ibgeCode, Integer bedroomNum, Integer livingRoomNum, Integer kitchenNum, Integer bathroomNum, Integer backyardNum, Integer otherNum, String observations, Long service, LocalDateTime createdAt, LocalDateTime updatedAt, UserDailyResponse client, UserDailyResponse housekeeper, List<RatingResponse> clientRatings) {
        super(id, status, cancellationReason, serviceName, attendanceDate, attendanceTime, price, street, number, neighborhood, complement, city, state, zipCode, ibgeCode, bedroomNum, livingRoomNum, kitchenNum, bathroomNum, backyardNum, otherNum, observations, service, createdAt, updatedAt, client, housekeeper);
        this.clientRatings = clientRatings;
    }

    public List<RatingResponse> getClientRatings() {
        return clientRatings;
    }

    public void setClientRatings(List<RatingResponse> clientRatings) {
        this.clientRatings = clientRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpportunityResponse that = (OpportunityResponse) o;
        return Objects.equals(clientRatings, that.clientRatings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientRatings);
    }
}
