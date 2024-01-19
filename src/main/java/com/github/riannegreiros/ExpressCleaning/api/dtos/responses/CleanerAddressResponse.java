package com.github.riannegreiros.ExpressCleaning.api.dtos.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CleanerAddressResponse {
    private Long id;
    private String street;
    private String number;
    private String neighborhood;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
}
