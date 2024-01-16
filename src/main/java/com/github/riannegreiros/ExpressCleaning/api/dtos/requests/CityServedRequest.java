package com.github.riannegreiros.ExpressCleaning.api.dtos.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonNaming(SnakeCaseStrategy.class)
public class CityServedRequest {
    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String ibgeCode;

    public CityServedRequest() {
    }

    public CityServedRequest(String city, String ibgeCode) {
        this.city = city;
        this.ibgeCode = ibgeCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(String ibgeCode) {
        this.ibgeCode = ibgeCode;
    }
}
