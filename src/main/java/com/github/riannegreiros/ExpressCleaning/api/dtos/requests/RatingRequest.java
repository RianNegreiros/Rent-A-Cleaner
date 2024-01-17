package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;

@JsonNaming(SnakeCaseStrategy.class)
public class RatingRequest {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @PositiveOrZero
    @Max(value = 5)
    private Double rate;

    public RatingRequest() {
    }

    public RatingRequest(String description, Double rate) {
        this.description = description;
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
